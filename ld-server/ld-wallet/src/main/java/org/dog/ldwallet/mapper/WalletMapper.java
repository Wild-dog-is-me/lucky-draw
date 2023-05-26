package org.dog.ldwallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.dog.ldwallet.po.Wallet;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/26 15:39
 * @Description:
 */

public interface WalletMapper  extends BaseMapper<Wallet> {
    int updateBalance(@Param("userId") Long userId, @Param("updateMoney") BigDecimal updateMoney, @Param("balance") BigDecimal balance);

    List<Long> notInitUserList();
}
