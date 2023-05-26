package org.dog.ldwallet.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.DistributedLock;
import org.dog.ldwallet.service.WalletService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/26 18:05
 * @Description:
 */
@Component
@AllArgsConstructor
@Slf4j
public class InitWalletScheduled {

    private final WalletService walletService;


    @DistributedLock
    @Scheduled(cron = "0 0 0/1 * * ?")
    void initWallet(){
        walletService.initAllNotWalletUser();
    }

}
