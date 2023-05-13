package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 13:29
 * @Description:
 */

@Data
public class ActivityConfigUpdateCmd extends Command {

    private ActivityUpdateCmd activityUpdateCmd;

    private List<Long> ruleIdList;

    private List<AwardUpdateCmd> awardUpdateCmdList;

}
