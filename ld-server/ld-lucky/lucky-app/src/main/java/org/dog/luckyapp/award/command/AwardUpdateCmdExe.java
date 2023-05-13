package org.dog.luckyapp.award.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyclient.dto.cmd.AwardUpdateCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:33
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardUpdateCmdExe {

    private final AwardGateway awardGateway;

    public AwardVO execute(AwardUpdateCmd cmd) {
        AwardEntity entity = awardGateway.save(AwardAssembler.toUpdateEntity(cmd));
        return AwardAssembler.toAwardVO(entity);
    }
}
