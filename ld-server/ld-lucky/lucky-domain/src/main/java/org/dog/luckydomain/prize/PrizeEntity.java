package org.dog.luckydomain.prize;

import com.alibaba.cola.domain.Entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/5 12:53
 * @Description:
 */

@Entity
@Data
public class PrizeEntity {
    /**
     *
     */
    private Long id;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 库存
     */
    private Inventory inventory;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 类型（1：商品，2：金钱）
     */
    private Integer type;

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
