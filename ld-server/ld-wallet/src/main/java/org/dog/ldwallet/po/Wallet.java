package org.dog.ldwallet.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/26 15:38
 * @Description:
 */

@Data
@TableName(value = "bld_user_wallet")
public class Wallet {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private BigDecimal balance;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String updater;
}
