package org.dog.luckyapp.activity.cmd;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.RecordStatusEnum;
import org.dog.config.exception.LdException;
import org.dog.config.util.AssertUtil;
import org.dog.config.util.SecurityUtil;
import org.dog.luckyapp.assembler.RecordAssembler;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyapp.record.command.RecordAddCmdExe;
import org.dog.luckyclient.dto.cmd.RecordAddCmd;
import org.dog.luckyclient.dto.data.*;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.activity.ActivityStatusEnum;
import org.dog.luckydomain.activity.ActivityTime;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.gateway.RecordGateway;
import org.dog.luckydomain.record.RecordEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Odin
 * @Date: 2023/5/15 14:53
 * @Description:
 */

@Getter
@Slf4j
@Component
@AllArgsConstructor
public class DefaultDrawExe extends BaseDrawExe {

    private final RecordGateway recordGateway;
    private final AwardGateway awardGateway;
    private final PrizeGateway prizeGateway;
    private final TransactionTemplate transactionTemplate;


    @Override
    protected void addRecord(ActivityDrawContext context) {
        // 插入记录，默认记录可见
        if (Objects.isNull(context.getIsShow())) {
            context.setIsShow(Boolean.TRUE);
        }
        RecordAddCmd recordAddCmd = new RecordAddCmd();
        recordAddCmd.setUserId(SecurityUtil.getUserId());
        recordAddCmd.setActivityId(context.getActivityConfigVO().getActivityVO().getId());
        recordAddCmd.setActivityName(context.getActivityConfigVO().getActivityVO().getActivityName());
        recordAddCmd.setAwardId(context.getAwardVO().getId());
        recordAddCmd.setIsWinning(Boolean.TRUE.equals(context.getAwardEntity().isPrize()) ? 1 : 0);
        recordAddCmd.setState(context.getIsShow() ? RecordStatusEnum.STATUE_1.getValue() : RecordStatusEnum.STATUE_0.getValue());

        context.setRecordId(recordGateway.save(RecordAssembler.toAddEntity(recordAddCmd)).getId());
    }

    @Override
    protected DrawResultVO addRecordAndGetDrawResultVO(ActivityDrawContext context) {

        DrawResultVO resultVO = transactionTemplate.execute(status -> {

            DrawResultVO drawResultVO = null;
            try {
                addRecord(context);
                drawResultVO = getDrawResultVO(context.getAwardEntity());
            } catch (Exception e) {
                //错误处理
                status.setRollbackOnly();
                log.error("插入抽奖记录或封装抽奖结果失败！", e);
            }
            return drawResultVO;
        });

        AssertUtil.isTrue(Objects.isNull(resultVO), "抱歉访问人数过多稍后再来！");

        return resultVO;
    }

    @Override
    protected Boolean drawBefore(ActivityDrawContext context) {

        return transactionTemplate.execute(status -> {
            Boolean seccess = Boolean.TRUE;
            int update = 0;
            try {
                // 扣减库存
                update = awardGateway.deductionAwardNumber(context.getAwardVO().getId(), 1);
                AssertUtil.isTrue(update != 1, "扣减库存失败！");
                addRecord(context);
            } catch (Exception e) {
                //错误处理
                status.setRollbackOnly();
                if (update > 0){
                    // 回退库存
                    awardGateway.deductionAwardNumber(context.getAwardVO().getId(), -1);
                }
                log.error("扣减库存和插入记录出错", e);
                seccess = Boolean.FALSE;
            }
            return seccess;
        });
    }

    @Override
    protected DrawResultVO getDrawResultVO(AwardEntity awardEntity) {
        DrawResultVO drawResultVO = new DrawResultVO();
        drawResultVO.setAwardName(awardEntity.getAwardName());
        if (Objects.nonNull(awardEntity.getPrizeId()) && !awardEntity.getPrizeId().toString().equals("0")) {
            drawResultVO.setPrizeName(prizeGateway.one(awardEntity.getPrizeId()).getPrizeName());
        }
        drawResultVO.setIsDraw(awardEntity.isPrize());
        return drawResultVO;
    }

    @Override
    protected AwardVO getAward(List<AwardVO> awardVOList) {
        // 抽奖算法
        /*
        1、权重
         */
        List<WeightRandom.WeightObj<AwardVO>> weightList = new ArrayList<>();
        awardVOList.forEach(item -> weightList.add(new WeightRandom.WeightObj<>(item, item.getProbability())));
        //创建带有权重的随机生成器
        WeightRandom<AwardVO> wr = RandomUtil.weightRandom(weightList);
        return wr.next();
    }

    @Override
    protected void checkActivityRule(ActivityConfigVO activityConfigVO) {
        List<RuleVO> ruleVOList = activityConfigVO.getRuleVOList();
        if (CollUtil.isEmpty(ruleVOList)) {
            return;
        }
        // 获取活动第一个规则
        RuleVO ruleVO = ruleVOList.get(0);

        final var query = new RecordListByParamQuery();
        query.setUserId(SecurityUtil.getUserId());
        query.setActivityId(activityConfigVO.getActivityVO().getId());
        query.setPageSize(1000);
        IPage<RecordEntity> page = recordGateway.page(query);

        // 校验最大参与次数
        AssertUtil.isTrue(page.getRecords().size() >= ruleVO.getMaxJoinNumber(), "你已达到活动最大参与次数，不可参与！");

        List<RecordEntity> winningRecordList = page.getRecords()
                .stream().filter(item -> item.getIsWinning() == 1)
                .collect(Collectors.toList());

        // 校验最大中奖次数
        AssertUtil.isTrue(winningRecordList.size() >= ruleVO.getMaxWinningNumber(), "你已达到最大中奖次数，不可参与！");
    }

    @Override
    protected void checkActivityTime(ActivityVO activityVO) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityTime(new ActivityTime(activityVO.getStartTime(), activityVO.getEndTime()));
        ActivityStatusEnum activityStatus = activityEntity.getActivityTime().getStatus();
        if (!ActivityStatusEnum.START.equals(activityStatus)) {
            throw new LdException(String.format("活动%s", activityStatus.getDescription()));
        }
    }
}
