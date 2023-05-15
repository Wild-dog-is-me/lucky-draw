package org.dog.luckyclient.api;

import org.dog.luckyclient.dto.cmd.ActivityConfigAddCmd;
import org.dog.luckyclient.dto.cmd.ActivityConfigUpdateCmd;
import org.dog.luckyclient.dto.cmd.ActivityUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityConfigCopyVO;
import org.dog.luckyclient.dto.data.ActivityConfigVO;

/**
 * @Author: Odin
 * @Date: 2023/5/9 13:26
 * @Description:
 */

public interface IActivityConfigService {

    ActivityConfigVO add(ActivityConfigAddCmd cmd);

    ActivityConfigVO update(ActivityConfigUpdateCmd cmd);

    ActivityConfigVO one(Long id);

    ActivityConfigCopyVO copy(Long id);

}
