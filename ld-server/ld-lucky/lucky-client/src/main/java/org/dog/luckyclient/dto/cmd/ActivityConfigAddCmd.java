package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 13:26
 * @Description:
 */

@Data
public class ActivityConfigAddCmd extends Command {

    private ActivityAddCmd activityAddCmd;

    private List<Long> ruleIdList;

    private List<AwardAddCmd> awardAddCmdList;

}
