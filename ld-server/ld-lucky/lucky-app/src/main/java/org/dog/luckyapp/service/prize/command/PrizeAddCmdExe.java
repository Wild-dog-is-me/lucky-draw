package org.dog.luckyapp.service.prize.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.PrizeAssembler;
import org.dog.luckyclient.dto.cmd.PrizeAddCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.prize.PrizeEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/5 13:18
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class PrizeAddCmdExe {

    private final PrizeGateway prizeGateway;

    public PrizeVO excute(PrizeAddCmd cmd) {
        PrizeEntity prizeEntity = prizeGateway.save(PrizeAssembler.toAddEntity(cmd));
        return PrizeAssembler.toPrizeVO(prizeEntity);
    }
}
