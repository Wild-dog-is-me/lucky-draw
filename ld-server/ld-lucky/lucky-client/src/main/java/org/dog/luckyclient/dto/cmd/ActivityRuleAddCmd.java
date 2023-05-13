package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:30
 * @Description:
 */

@Data
public class ActivityRuleAddCmd extends Command {

    private Long activityId;

    private Long ruleId;

}
