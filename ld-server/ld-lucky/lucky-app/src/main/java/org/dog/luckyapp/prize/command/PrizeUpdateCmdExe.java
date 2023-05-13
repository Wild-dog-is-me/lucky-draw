package org.dog.luckyapp.prize.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.PrizeAssembler;
import org.dog.luckyclient.dto.cmd.PrizeUpdateCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.prize.PrizeEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/5 14:02
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class PrizeUpdateCmdExe {

    private final PrizeGateway prizeGateway;

    public PrizeVO excute(PrizeUpdateCmd cmd) {
        PrizeEntity entity = prizeGateway.save(PrizeAssembler.toUpdateEntity(cmd));
        return PrizeAssembler.toPrizeVO(entity);
    }

}
