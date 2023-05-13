package org.dog.luckydomain.activityrule;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:18
 * @Description:
 */
@Entity
@Data
public class ActivityRuleEntity {

    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;

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
