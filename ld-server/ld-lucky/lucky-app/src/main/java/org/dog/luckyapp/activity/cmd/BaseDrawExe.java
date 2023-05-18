package org.dog.luckyapp.activity.cmd;

import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.data.DrawResultVO;
import org.dog.luckydomain.award.AwardEntity;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/15 14:16
 * @Description:
 */

@Slf4j
public abstract class BaseDrawExe {

    /**
     * 抽奖模板方法，流程不会改动
     *
     * @param context
     * @return
     */
    public DrawResultVO execute(ActivityDrawContext context) {

        // 校验活动时间
        checkActivityTime(context.getActivityConfigVO().getActivityVO());
        // 校验活动规则
        checkActivityRule(context.getActivityConfigVO());
        // 剔除奖项库存为空的奖项
        List<AwardVO> awardVOList = removeAwardInventoryNull(context.getActivityConfigVO().getAwardVOList());
        // 调用算法进行抽奖
        context.setAwardVO(getAward(awardVOList));

        context.setAwardEntity(AwardAssembler.toAwardEntity(context.getAwardVO()));

        context.setIsWinTheLottery(context.getAwardEntity().isPrize());

        if (Boolean.FALSE.equals(context.getIsWinTheLottery())) {
            // 插入记录
            addRecord(context);
            return getDrawResultVO(context.getAwardEntity());
        }

        Boolean drawBefore = Boolean.TRUE;
        try {
            // 调用后续抽奖流程，流程内容自定义
            drawBefore = drawBefore(context);
        } catch (Exception e) {
            drawBefore = Boolean.FALSE;
            log.error("执行【drawBefore】方法出错,默认返回未中奖信息", e);
        }
        if (Boolean.FALSE.equals(drawBefore)) {
            // 执行 drawBefore 出错，默认返回未中奖
            DrawResultVO resultVO = getDefaultDrawResultVO(context.getActivityConfigVO().getAwardVOList());
            AwardVO notAward = getNotAward(context.getActivityConfigVO().getAwardVOList());
            context.setAwardVO(notAward);
            context.setAwardEntity(AwardAssembler.toAwardEntity(notAward));
            context.setIsWinTheLottery(Boolean.FALSE);
            // 插入记录
            addRecord(context);
            return getDrawResultVO(context.getAwardEntity());
        }

        return getDrawResultVO(context.getAwardEntity());
    }

    protected abstract void addRecord(ActivityDrawContext context);

    protected abstract Boolean drawBefore(ActivityDrawContext context);

    protected abstract DrawResultVO getDrawResultVO(AwardEntity awardEntity);

    protected abstract AwardVO getAward(List<AwardVO> awardVOList);

    protected abstract List<AwardVO> removeAwardInventoryNull(List<AwardVO> awardVOList);

    protected abstract void checkActivityRule(ActivityConfigVO activityConfigVO);

    /**
     * 校验活动
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


}
