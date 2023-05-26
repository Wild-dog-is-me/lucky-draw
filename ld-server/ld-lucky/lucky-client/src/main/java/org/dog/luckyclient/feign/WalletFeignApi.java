package org.dog.luckyclient.feign;

import org.dog.config.constant.ServerNameConstants;
import org.dog.luckyclient.feign.form.UpdateWalletForm;
import org.dog.luckyclient.feign.vo.WalletUpdateResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/5/26 17:41
 * @Description:
 */
@FeignClient(name = ServerNameConstants.WALLET, path = "/v1/feign/wallet")
public interface WalletFeignApi {

    @PostMapping("/updateWallet")
    WalletUpdateResultVO updateBalance(@RequestBody UpdateWalletForm form);

    @GetMapping("/initUserWallet")
    void initUserWallet(@RequestParam("userId") Long userId);

}
