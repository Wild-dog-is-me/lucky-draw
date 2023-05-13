package org.dog.luckyinfrastructure.gateway.impl.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @TableName bld_activity
 */
@TableName(value ="bld_activity")
@Data
@Getter
public class ActivityDB implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
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
    @TableField("`describe`")
    private String describe;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     *
     */
    private String updater;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
