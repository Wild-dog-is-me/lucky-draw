package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:08
 * @Description:
 */

@Data
public class AwardVO {

    /**
     *
     */
    private Long id;

    /**
     * 奖品名称
     */
    private Long prizeId;
    private String prizeName;
    private Long activityId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 奖项名称
     */
    private String awardName;

    /**
     * 概率（1，0.001）
     */
    private Double probability;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    private LocalDateTime updateTime;

    /**
     *
     */
    private String updater;

}
