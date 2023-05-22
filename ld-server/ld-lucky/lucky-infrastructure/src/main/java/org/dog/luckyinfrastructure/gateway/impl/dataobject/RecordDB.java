package org.dog.luckyinfrastructure.gateway.impl.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName bld_record
 */
@TableName(value ="bld_record")
@Data
public class RecordDB implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;

    private String activityName;

    /**
     * 奖项id
     */
    private Long awardId;

    @TableField(exist = false)
    private String awardName;

    @TableField(exist = false)
    private String prizeName;

    @TableField(exist = false)
    private Integer prizeType;

    /**
     * 是否中奖：0未中奖，1中奖
     */
    private Integer isWinning;

    /**
     * 状态（0，1，2，3）
     */
    private Integer state;

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
