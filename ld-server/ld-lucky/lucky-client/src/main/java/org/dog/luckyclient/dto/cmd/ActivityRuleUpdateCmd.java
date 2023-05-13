package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:35
 * @Description:
 */

@Data
public class ActivityRuleUpdateCmd extends Command {

    private Long id;  

    private Long activityId;

    private Long ruleId;

}
