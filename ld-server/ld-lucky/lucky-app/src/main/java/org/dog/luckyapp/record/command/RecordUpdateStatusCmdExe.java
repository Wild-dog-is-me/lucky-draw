package org.dog.luckyapp.record.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckydomain.gateway.RecordGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/21 16:55
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RecordUpdateStatusCmdExe {

    private final RecordGateway recordGateway;

    public Boolean execute(RecordUpdateStatusCmd cmd) {
        Boolean updateStatus = recordGateway.updateStatus(cmd.getId(), cmd.getState());
        log.info("修改记录失败：记录id：{}，状态值：{}", cmd.getId(), cmd.getState());
        return updateStatus;
    }
}
