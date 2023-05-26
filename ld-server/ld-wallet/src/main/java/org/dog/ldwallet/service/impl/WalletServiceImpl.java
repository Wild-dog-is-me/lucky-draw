package org.dog.ldwallet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.util.SecurityUtil;
import org.dog.ldwallet.api.fegin.form.UpdateWalletForm;
import org.dog.ldwallet.api.fegin.vo.WalletUpdateResultVO;
import org.dog.ldwallet.mapper.WalletMapper;
import org.dog.ldwallet.po.Wallet;
import org.dog.ldwallet.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/26 16:03
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {


    @Override
    public WalletUpdateResultVO updateBalance(UpdateWalletForm form) {
        Wallet wallet = lambdaQuery()
                .eq(Wallet::getUserId, form.getUserId())
                .one();
        if (Objects.isNull(wallet)) {
            try {
                wallet = initWallet(form.getUserId());
            } catch (Exception e) {
                log.error("执行用户钱包初始化失败", e);
                wallet = lambdaQuery()
                        .eq(Wallet::getUserId, form.getUserId())
                        .one();
            }
        }

        if (Objects.isNull(wallet)) {
            return new WalletUpdateResultVO()
                    .setResult(false);
        }

        int updateBalance = getBaseMapper().updateBalance(form.getUserId(), form.getUpdateMoney(), wallet.getBalance());

        if (updateBalance != 1) {
            return new WalletUpdateResultVO()
                    .setResult(Boolean.FALSE);
        }

        return new WalletUpdateResultVO()
                .setResult(Boolean.TRUE);
    }

    private Wallet initWallet(Long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(new BigDecimal(0));
        wallet.setCreateTime(LocalDateTime.now());
        wallet.setUpdateTime(LocalDateTime.now());
        wallet.setUpdater("管理员");
        save(wallet);
        return wallet;
    }

    @Override
    public void initUserWallet(Long userId) {
        initWallet(userId);
    }

    @Override
    public void initAllNotWalletUser() {
        List<Long> noInitUserList = getBaseMapper().notInitUserList();
        log.info(noInitUserList.toString());
        noInitUserList.stream().parallel().forEach(this::initWallet);
    }
}
