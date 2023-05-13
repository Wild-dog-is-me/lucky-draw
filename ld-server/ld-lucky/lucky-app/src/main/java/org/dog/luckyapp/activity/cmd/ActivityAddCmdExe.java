package org.dog.luckyapp.activity.cmd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.ActivityAssembler;
import org.dog.luckyclient.dto.cmd.ActivityAddCmd;
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
public class ActivityAddCmdExe {

    private ActivityGateway activityGateway;

    public ActivityVO execute(ActivityAddCmd cmd) {
        ActivityEntity entity = activityGateway.save(ActivityAssembler.toAddEntity(cmd));
        return ActivityAssembler.toActivityVO(entity);
    }


}
