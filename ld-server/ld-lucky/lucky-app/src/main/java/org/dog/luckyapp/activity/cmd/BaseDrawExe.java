package org.dog.luckyapp.activity.cmd;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.data.DrawResultVO;
import org.dog.luckydomain.award.AwardEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Odin
 * @Date: 2023/5/15 14:16
 * @Description:
 */

@Slf4j
public abstract class BaseDrawExe {


    /**
     * 抽奖模板方法
     *
     * @param context
     */
    public final DrawResultVO execute(ActivityDrawContext context) {
        // 校验活动时间
        checkActivityTime(context.getActivityConfigVO().getActivityVO());
        // 校验活动规则
        checkActivityRule(context.getActivityConfigVO());
        // 剔除奖项库存为空的项
        List<AwardVO> awardVOList = removeAwardInventoryNull(context.getActivityConfigVO().getAwardVOList());
        // 调用抽奖算法进行抽奖

        context.setAwardVO(getAward(awardVOList));
        log.error("【context.setAwardVO(getAward(awardVOList))】===> {}", getAward(awardVOList));
        context.setAwardEntity(AwardAssembler.toAwardEntity(context.getAwardVO()));
        context.setIsWinTheLottery(context.getAwardEntity().isPrize());
        if (Boolean.FALSE.equals(context.getIsWinTheLottery())) {
            // 插入未中奖记录
            return addRecordAndGetDrawResultVO(context);
        }

        Boolean drawBefore = Boolean.TRUE;
        try {
            // 调用抽奖后续流程，流程内容自定义
            drawBefore = drawBefore(context);
        } catch (Exception e) {
            //错误处理
            drawBefore = Boolean.FALSE;
            log.error("执行drawBefore方法出错，默认返回未中奖信息，", e);
        }

        if (Boolean.FALSE.equals(drawBefore)) {
            // 执行 drawBefore 出错，默认返回未中奖
            context.setAwardVO(getNotAward(context.getActivityConfigVO().getAwardVOList()));
            log.error("【AwardVO】===> {}", context.getAwardVO());
            context.setAwardEntity(AwardAssembler.toAwardEntity(context.getAwardVO()));
            context.setIsWinTheLottery(Boolean.FALSE);
            return addRecordAndGetDrawResultVO(context);
        }

        // 返回结果
        return getDrawResultVO(context.getAwardEntity());
    }

    protected abstract DrawResultVO addRecordAndGetDrawResultVO(ActivityDrawContext context);

    protected abstract Boolean drawBefore(ActivityDrawContext context);

    protected abstract void addRecord(ActivityDrawContext context);

    protected abstract DrawResultVO getDrawResultVO(AwardEntity awardEntity);

    protected abstract AwardVO getAward(List<AwardVO> awardVOList);

    protected abstract void checkActivityRule(ActivityConfigVO activityConfigVO);

    /**
     * 校验活动
     *
     * @param activityVO
     */
    protected abstract void checkActivityTime(ActivityVO activityVO);



    private AwardVO getNotAward(List<AwardVO> awardVOList) {
        for (AwardVO awardVO : awardVOList) {
            if ("0".equals(awardVO.getPrizeId().toString())) {
                return awardVO;
            }
        }
        return null;
    }

    private DrawResultVO getDefaultDrawResultVO(List<AwardVO> awardVOList) {
        return getDrawResultVO(AwardAssembler.toAwardEntity(getNotAward(awardVOList)));
    }

    private  List<AwardVO> removeAwardInventoryNull(List<AwardVO> awardVOList){
        if (CollectionUtil.isEmpty(awardVOList)) {
            return new ArrayList<>();
        }
        return awardVOList.stream()
                .filter(item -> item.getNumber() > 0 || "0".equals(item.getPrizeId().toString()))
                .collect(Collectors.toList());
    }

}

