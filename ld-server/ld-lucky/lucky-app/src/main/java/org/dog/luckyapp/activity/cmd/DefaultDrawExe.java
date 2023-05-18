package org.dog.luckyapp.activity.cmd;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.config.util.AssertUtil;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyclient.dto.data.*;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.activity.ActivityStatusEnum;
import org.dog.luckydomain.activity.ActivityTime;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
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

    public void addRecord(ActivityDrawContext context) {

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
