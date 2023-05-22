package org.dog.luckyapp.activity.cmd;

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

@Slf4j
@Getter
@Component
@AllArgsConstructor
public class DefaultDrawExe extends BaseDrawExe {

    private final RecordGateway recordGateway;
    private final AwardGateway awardGateway;
    private final PrizeGateway prizeGateway;
    private final TransactionTemplate transactionTemplate;

    @Override
    protected Boolean drawBefore(ActivityDrawContext context) {
        // 编程式事务
        return transactionTemplate.execute(status -> {
            Boolean seccess = Boolean.TRUE;
            int update = 0;
            try {
                // 扣减库存
                update = awardGateway.deductionAwardNumber(context.getAwardVO().getId(), 1);
                AssertUtil.isTrue(update != 1, "扣减库存失败！");
                addRecord(context);
                context.setIsShow(Boolean.TRUE);
            } catch (Exception e) {
                //错误处理
                status.setRollbackOnly();
                if (update > 0) {
                    // 回退库存
                    awardGateway.deductionAwardNumber(context.getAwardVO().getId(), -1);
                }
                log.error("扣减库存和插入记录出错", e);
                seccess = Boolean.FALSE;
            }
            return seccess;
        });
    }

    public void addRecord(ActivityDrawContext context) {

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

    protected DrawResultVO getDrawResultVO(AwardEntity awardEntity) {
        DrawResultVO drawResultVO = new DrawResultVO();
        drawResultVO.setAwardName(awardEntity.getAwardName());
        drawResultVO.setPrizeName(prizeGateway.one(awardEntity.getId()).getPrizeName());
        drawResultVO.setIsDraw(awardEntity.isPrize());
        return drawResultVO;
    }

    protected AwardVO getAward(List<AwardVO> awardVOList) {
        List<WeightRandom.WeightObj<AwardVO>> weightList = new ArrayList<>();
        awardVOList.forEach(item -> weightList.add(new WeightRandom.WeightObj<>(item, item.getProbability())));
        WeightRandom<AwardVO> wr = RandomUtil.weightRandom(weightList);
        return wr.next();
    }

    protected List<AwardVO> removeAwardInventoryNull(List<AwardVO> awardVOList) {
        if (CollectionUtil.isEmpty(awardVOList)) {
            return new ArrayList<>();
        }
        return awardVOList.stream()
                .filter(item -> item.getNumber() > 0 || "0".equals(item.getPrizeId().toString()))
                .collect(Collectors.toList());
    }

    protected void checkActivityRule(ActivityConfigVO activityConfigVO) {
        List<RuleVO> ruleVOList = activityConfigVO.getRuleVOList();
        if (CollectionUtil.isEmpty(ruleVOList)) {
            return;
        }
        RuleVO ruleVO = ruleVOList.get(0);
        final var query = new RecordListByParamQuery();
        query.setUserId(SecurityUtil.getUserId());
        query.setActivityId(activityConfigVO.getActivityVO().getId());
        query.setPageSize(1000);
        IPage<RecordEntity> page = recordGateway.page(query);
        AssertUtil.isTrue(page.getRecords().size() >= ruleVO.getMaxJoinNumber(), "您已达到活动最大参与次数，现不可参与");
        List<RecordEntity> winningRecordList = page.getRecords()
                .stream().filter(item -> item.getIsWinning() == 1)
                .collect(Collectors.toList());
        AssertUtil.isTrue(winningRecordList.size() >= ruleVO.getMaxWinningNumber(),"您已达到活动最大中奖次数，不可参与");

    }

    protected void checkActivityTime(ActivityVO activityVO) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityTime(new ActivityTime(activityVO.getStartTime(), activityVO.getEndTime()));
        ActivityStatusEnum status = activityEntity.getActivityTime().getStatus();
        if (ActivityStatusEnum.START.equals(status)) {
            throw new LdException(String.format("活动%s", status.getDescription()));
        }

    }
}
