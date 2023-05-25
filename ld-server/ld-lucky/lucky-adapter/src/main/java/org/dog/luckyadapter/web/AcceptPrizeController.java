package org.dog.luckyadapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IAcceptPrizeService;
import org.dog.luckyclient.dto.cmd.AcceptPrizeAddCmd;
import org.dog.luckyclient.dto.data.AcceptPrizeVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/26 00:01
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/acceptPrize")
public class AcceptPrizeController {

    private final IAcceptPrizeService acceptPrizeService;


    @PostMapping("/add")
    public AcceptPrizeVO add(@Validated @RequestBody AcceptPrizeAddCmd cmd) {
        return acceptPrizeService.add(cmd);
    }

    @GetMapping("/one")
    public AcceptPrizeVO one(@RequestParam("recordId") Long recordId) {
        return acceptPrizeService.one(recordId);
    }

}
