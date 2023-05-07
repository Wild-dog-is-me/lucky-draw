package org.dog.luckyapp.service.award.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:32
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardAddCmdExe {

    private AwardGateway awardGateway;

    public AwardVO execute(AwardAddCmd cmd) {
        AwardEntity entity = awardGateway.save(AwardAssembler.toAddEntity(cmd));
        return AwardAssembler.toAwardVO(entity);
    }
}
