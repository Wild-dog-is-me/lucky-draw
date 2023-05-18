package org.dog.luckyapp.context;

import lombok.Data;
import lombok.experimental.Accessors;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckydomain.award.AwardEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/16 23:42
 * @Description:
 */

@Data
@Accessors(chain = true)
public class ActivityDrawContext {

    private ActivityConfigVO activityConfigVO;

    /**
     * 抽奖算法获得到的奖项
     */
    private AwardVO awardVO;
    /**
     * 抽奖算法获得到的奖项entity
     */
    private AwardEntity awardEntity;

    /**
     * 是否中奖,true:中奖
     */
    private Boolean isWinTheLottery;

    /**
     * 是否可见，用户中奖日志是否可见
     */
    private Boolean isShow;

    /**
     * 中奖记录id
     */
    private Long recordId;

}
