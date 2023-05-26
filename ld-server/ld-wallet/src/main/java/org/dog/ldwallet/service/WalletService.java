package org.dog.ldwallet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.ldwallet.api.fegin.form.UpdateWalletForm;
import org.dog.ldwallet.api.fegin.vo.WalletUpdateResultVO;
import org.dog.ldwallet.po.Wallet;

/**
 * @Author: Odin
 * @Date: 2023/5/26 16:01
 * @Description:
 */
public interface WalletService extends IService<Wallet> {

    WalletUpdateResultVO updateBalance(UpdateWalletForm form);

    void initUserWallet(Long userId);

    void initAllNotWalletUser();

}
