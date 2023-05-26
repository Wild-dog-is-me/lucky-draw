package org.dog.ldwallet.api.fegin;

import lombok.AllArgsConstructor;
import org.dog.ldwallet.api.fegin.form.UpdateWalletForm;
import org.dog.ldwallet.api.fegin.vo.WalletUpdateResultVO;
import org.dog.ldwallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/26 17:38
 * @Description:
 */
@RestController
@RequestMapping("/v1/feign/wallet")
@AllArgsConstructor
public class WalletFeign {

    private final WalletService walletService;

    @PostMapping("/updateWallet")
    public WalletUpdateResultVO updateBalance(@RequestBody UpdateWalletForm form) {
        return walletService.updateBalance(form);
    }

    @GetMapping("/initUserWallet")
    public void initUserWallet(@RequestParam("userId") Long userId) {
        walletService.initUserWallet(userId);
    }

}
