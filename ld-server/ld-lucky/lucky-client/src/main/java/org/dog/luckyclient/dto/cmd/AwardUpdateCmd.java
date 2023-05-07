package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:10
 * @Description:
 */
@Data
public class AwardUpdateCmd extends Command {

    @NotNull(message = "id不为空")
    private Long id;

    /**
     * 奖品名称
     */
    @NotNull(message = "奖品id不为空")
    private Long prizeId;

    @NotNull(message = "活动id不为空")
    private Long activityId;

    /**
     * 数量
     */
    @NotNull(message = "数量不为空")
    private Integer number;

    /**
     * 奖项名称
     */
    @NotNull(message = "奖项名称不为空")
    private String awardName;

    /**
     * 概率
     */
    @NotNull(message = "概率不为空")
    private Double probability;
}
