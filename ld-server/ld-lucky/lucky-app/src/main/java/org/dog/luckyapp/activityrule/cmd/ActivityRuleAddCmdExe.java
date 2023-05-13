package org.dog.luckyapp.activityrule.cmd;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.luckyapp.assembler.ActivityRuleAssembler;
import org.dog.luckyclient.dto.cmd.ActivityRuleAddCmd;
import org.dog.luckyclient.dto.data.ActivityRuleVO;
import org.dog.luckydomain.activityrule.ActivityRuleEntity;
import org.dog.luckydomain.gateway.ActivityRuleGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:57
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleAddCmdExe {

    private final ActivityRuleGateway activityRuleGateway;

    public ActivityRuleVO execute(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity entity = activityRuleGateway.save(ActivityRuleAssembler.toAddEntity(cmd));
        return ActivityRuleAssembler.toActivityRuleVO(entity);
    }

    public List<ActivityRuleVO> execute(List<ActivityRuleAddCmd> cmdList) {
        if (CollUtil.isEmpty(cmdList)) {
            throw new LdException("数据有误");
        }
        List<ActivityRuleVO> result = new ArrayList<>();
        for (ActivityRuleAddCmd addCmd : cmdList) {
            result.add(execute(addCmd));
        }
        return result;
    }

}
