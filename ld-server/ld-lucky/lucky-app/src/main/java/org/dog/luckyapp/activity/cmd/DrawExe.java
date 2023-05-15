package org.dog.luckyapp.activity.cmd;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyclient.dto.data.*;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.activity.ActivityStatusEnum;
import org.dog.luckydomain.activity.ActivityTime;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Odin
 * @Date: 2023/5/15 09:57
 * @Description:
 */
@Slf4j
@Component
@AllArgsConstructor
public class DrawExe {

    private final AwardGateway awardGateway;
    private final PrizeGateway prizeGateway;

    public DrawResultVO execute(ActivityConfigVO activityConfigVO) {

        // 校验活动时间
        checkActivityTime(activityConfigVO.getActivityVO());
        // 校验活动规则
        checkActivityRule(activityConfigVO);
        // 剔除奖项库存为空的奖项
        List<AwardVO> awardVOList = removeAwardInventoryNull(activityConfigVO.getAwardVOList());
        // 调用算法进行抽奖
        AwardVO awardVO = getAward(awardVOList);
        AwardEntity awardEntity = AwardAssembler.toAwardEntity(awardVO);
        if (!awardEntity.isPrize()) {
            return getDrawResultVO(awardEntity);
        }
        // 扣除奖项库存
        if (deductionAwardAwardNumber(awardEntity.getId(), 1) != 1) {
            return getDefaultDrawResultVO(awardVOList);
        }
        // 插入获奖记录
        addAcceptPrize(activityConfigVO.getActivityVO().getId(), awardEntity);

        return getDrawResultVO(awardEntity);
    }

    private void addAcceptPrize(Long id, AwardEntity awardEntity) {

    }

    private DrawResultVO getDefaultDrawResultVO(List<AwardVO> awardVOList) {
        DrawResultVO result = new DrawResultVO();
        for (AwardVO awardVO : awardVOList) {
            if ("0".equals(awardVO.getPrizeId().toString())) {
                result = getDrawResultVO(AwardAssembler.toAwardEntity(awardVO));
                break;
            }
        }
        return result;
    }

    private int deductionAwardAwardNumber(Long awardId, Integer number) {
        return awardGateway.deductionAwardNumber(awardId, number);
    }

    private DrawResultVO getDrawResultVO(AwardEntity awardEntity) {
        DrawResultVO drawResultVO = new DrawResultVO();
        drawResultVO.setAwardName(awardEntity.getAwardName());
        drawResultVO.setPrizeName(prizeGateway.one(awardEntity.getId()).getPrizeName());
        drawResultVO.setIsDraw(awardEntity.isPrize());
        return drawResultVO;
    }

    private AwardVO getAward(List<AwardVO> awardVOList) {
        List<WeightRandom.WeightObj<AwardVO>> weightList = new ArrayList<>();
        awardVOList.forEach(item -> weightList.add(new WeightRandom.WeightObj<>(item, item.getProbability())));
        WeightRandom<AwardVO> wr = RandomUtil.weightRandom(weightList);
        return wr.next();
    }

    private List<AwardVO> removeAwardInventoryNull(List<AwardVO> awardVOList) {
        if (CollectionUtil.isEmpty(awardVOList)) {
            return new ArrayList<>();
        }
        return awardVOList.stream()
                .filter(item -> item.getNumber() > 0 || "0".equals(item.getPrizeId().toString()))
                .collect(Collectors.toList());

    }

    private void checkActivityRule(ActivityConfigVO activityConfigVO) {
        List<RuleVO> ruleVOList = activityConfigVO.getRuleVOList();
    }

    private void checkActivityTime(ActivityVO activityVO) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityTime(new ActivityTime(activityVO.getStartTime(), activityVO.getEndTime()));
        ActivityStatusEnum status = activityEntity.getActivityTime().getStatus();
        if (ActivityStatusEnum.START.equals(status)) {
            throw new LdException(String.format("活动%s", status.getDescription()));
        }

    }
}
