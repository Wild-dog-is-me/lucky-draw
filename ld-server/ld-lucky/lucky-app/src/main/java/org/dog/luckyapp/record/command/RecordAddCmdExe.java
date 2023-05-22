package org.dog.luckyapp.record.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.RecordAssembler;
import org.dog.luckyclient.dto.cmd.RecordAddCmd;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckydomain.gateway.RecordGateway;
import org.dog.luckydomain.record.RecordEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/21 12:21
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RecordAddCmdExe {

    private final RecordGateway recordGateway;

    public RecordVO execute(RecordAddCmd cmd) {
        RecordEntity entity = recordGateway.save(RecordAssembler.toAddEntity(cmd));
        return RecordAssembler.toRecordVO(entity);
    }

}
