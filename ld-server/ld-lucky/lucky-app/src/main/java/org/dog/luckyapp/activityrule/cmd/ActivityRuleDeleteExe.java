package org.dog.luckyapp.activityrule.cmd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckydomain.gateway.ActivityRuleGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/10 10:59
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleDeleteExe {

    private final ActivityRuleGateway activityRuleGateway;

    public void execute(Long activityId) {
        activityRuleGateway.deleteByActivityId(activityId);
    }
}
