package org.dog.luckyclient.dto.data;

import lombok.Data;
import org.dog.luckyclient.dto.cmd.ActivityAddCmd;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/14 23:39
 * @Description:
 */
@Data
public class ActivityConfigCopyVO {
    private ActivityAddCmd activityAddCmd;

    private List<Long> ruleIdList;

    private List<AwardAddCmd> awardAddCmdList;
}
