package org.dog.luckyapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.acceptprize.cmd.AcceptPrizeAddCmdExe;
import org.dog.luckyapp.acceptprize.query.AcceptPrizeOneExe;
import org.dog.luckyapp.record.command.RecordUpdateStatusCmdExe;
import org.dog.luckyclient.api.IAcceptPrizeService;
import org.dog.luckyclient.dto.cmd.AcceptPrizeAddCmd;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckyclient.dto.data.AcceptPrizeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Odin
 * @Date: 2023/5/26 00:08
 * @Description:
 */
@Service
@Slf4j
@AllArgsConstructor
public class AcceptPrizeServiceImpl implements IAcceptPrizeService {

    private final AcceptPrizeAddCmdExe acceptPrizeAddCmdExe;
    private final RecordUpdateStatusCmdExe recordUpdateStatusCmdExe;
    private final AcceptPrizeOneExe acceptPrizeOneExe;

    @Override
    public AcceptPrizeVO one(Long recordId) {
        return acceptPrizeOneExe.execute(recordId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AcceptPrizeVO add(AcceptPrizeAddCmd cmd) {

        AcceptPrizeVO execute = acceptPrizeAddCmdExe.execute(cmd);

        final var statusCmd = new RecordUpdateStatusCmd();
        statusCmd.setId(cmd.getRecordId());
        statusCmd.setState(2);
        recordUpdateStatusCmdExe.execute(statusCmd);

        return execute;
    }
}
