package org.dog.luckyapp.service.activity.cmd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.ActivityAssembler;
import org.dog.luckyclient.dto.cmd.ActivityUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.gateway.ActivityGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:33
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityUpdateCmdExe {

    private ActivityGateway activityGateway;

    public ActivityVO execute(ActivityUpdateCmd cmd) {
        ActivityEntity entity = activityGateway.save(ActivityAssembler.toUpdateEntity(cmd));
        return ActivityAssembler.toActivityVO(entity);
    }
}
