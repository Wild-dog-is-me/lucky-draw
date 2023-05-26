package org.dog.ldwallet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.ldwallet.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Odin
 * @Date: 2023/5/26 21:20
 * @Description:
 */

@Slf4j
@ResponseResult
@AllArgsConstructor
@RequestMapping("/v1/walletTest")
public class TestController {

    private final WalletService walletService;

    @GetMapping("/initWallet")
    private void initWallet() {
        walletService.initAllNotWalletUser();
    }
}
