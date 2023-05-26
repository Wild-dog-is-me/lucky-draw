package org.dog.ldwallet.api.fegin.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Odin
 * @Date: 2023/5/26 16:02
 * @Description:
 */
@Data
public class UpdateWalletForm {

    private Long userId;

    private BigDecimal updateMoney;
}
