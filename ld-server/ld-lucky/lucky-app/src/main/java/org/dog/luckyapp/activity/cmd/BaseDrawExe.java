package org.dog.luckyapp.activity.cmd;

import org.dog.luckyapp.assembler.AwardAssembler;
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
public abstract class BaseDrawExe {

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
        if (defaultDeductionAwardNumber(activityConfigVO.getActivityVO().getId(), awardEntity.getId()) != 1) {
            return getDefaultDrawResultVO(awardVOList);
        }
        // 插入获奖记录
        addAcceptPrize(activityConfigVO.getActivityVO().getId(), awardEntity);

        return getDrawResultVO(awardEntity);
    }

    protected abstract void addAcceptPrize(Long id, AwardEntity awardEntity);

    protected Integer defaultDeductionAwardNumber(Long activityId, Long awardId){
        return deductionAwardNumber(awardId, 1);
    };

    protected abstract int deductionAwardNumber(Long awardId, Integer number);

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
