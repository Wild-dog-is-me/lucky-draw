package org.dog.luckydomain.award;

import com.alibaba.cola.domain.Entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:04
 * @Description:
 */

@Data
@Entity
public class AwardEntity {

    /**
     *
     */
    private Long id;

    /**
     * 奖品名称
     */
    private Long prizeId;
    private Long activityId;

    /**
     * 数量
     */
    private AwardNumber number;

    /**
     * 奖项名称
     */
    private String awardName;

    /**
     * 概率
     */
    private Double probability;

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

    /**
     * 判断该奖项是否是一个奖品，也即该奖项是否中奖
     */
    public Boolean isPrize() {
        return !"0".equals(this.prizeId.toString());
    }

}
