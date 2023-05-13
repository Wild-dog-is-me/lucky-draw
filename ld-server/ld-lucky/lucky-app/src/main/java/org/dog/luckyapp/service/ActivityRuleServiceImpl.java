package org.dog.luckyapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.activityrule.cmd.ActivityRuleAddCmdExe;
import org.dog.luckyapp.activityrule.query.ActivityRuleListByParamQueryExe;
import org.dog.luckyclient.api.IActivityRuleService;
import org.dog.luckyclient.dto.cmd.ActivityRuleAddCmd;
import org.dog.luckyclient.dto.data.ActivityRuleVO;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:55
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleServiceImpl implements IActivityRuleService {

    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;

    @Override
    public ActivityRuleVO add(ActivityRuleAddCmd cmd) {
        return activityRuleAddCmdExe.execute(cmd);
    }

    @Override
    public List<ActivityRuleVO> list(ActivityRuleListByParamQuery query) {
        return activityRuleListByParamQueryExe.execute(query);
    }

}
