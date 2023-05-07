package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:02
 * @Description:
 */
@Data
public class ActivityVO {
    /**
     *
     */
    private Long id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 描述
     */
    private String describe;

    /**
     * 0,未开始，1，进行中，2已结束
     */
    private Integer status;


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
