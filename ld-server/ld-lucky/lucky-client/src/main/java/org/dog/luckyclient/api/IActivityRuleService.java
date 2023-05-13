package org.dog.luckyclient.api;

import org.dog.luckyclient.dto.cmd.ActivityRuleAddCmd;
import org.dog.luckyclient.dto.data.ActivityRuleVO;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:51
 * @Description:
 */
public interface IActivityRuleService {

    ActivityRuleVO add(ActivityRuleAddCmd cmd);

    List<ActivityRuleVO> list(ActivityRuleListByParamQuery query);
}

