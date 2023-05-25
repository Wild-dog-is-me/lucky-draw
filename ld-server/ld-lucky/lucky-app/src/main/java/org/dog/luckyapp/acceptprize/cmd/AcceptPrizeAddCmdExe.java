package org.dog.luckyapp.acceptprize.cmd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AcceptPrizeAssembler;
import org.dog.luckyclient.dto.cmd.AcceptPrizeAddCmd;
import org.dog.luckyclient.dto.data.AcceptPrizeVO;
import org.dog.luckydomain.acceptprize.AcceptPrizeEntity;
import org.dog.luckydomain.gateway.AcceptPrizeGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/26 00:00
 * @Description:
 */
@Component
@Slf4j
@AllArgsConstructor
public class AcceptPrizeAddCmdExe {

    private final AcceptPrizeGateway acceptPrizeGateway;

    public AcceptPrizeVO execute(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity entity = acceptPrizeGateway.save(AcceptPrizeAssembler.toEntity(cmd));

        return AcceptPrizeAssembler.toAcceptPrizeVO(entity);
    }
}
