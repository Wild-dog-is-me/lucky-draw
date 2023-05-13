package org.dog.luckyapp.assembler;

import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.dto.cmd.ActivityRuleAddCmd;
import org.dog.luckyclient.dto.data.ActivityRuleVO;
import org.dog.luckydomain.activityrule.ActivityRuleEntity;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/9 23:06
 * @Description:
 */

public class ActivityRuleAssembler {
    public static ActivityRuleEntity toAddEntity(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity activityRuleEntity = new ActivityRuleEntity();
        activityRuleEntity.setActivityId(cmd.getActivityId());
        activityRuleEntity.setRuleId(cmd.getRuleId());
        activityRuleEntity.setCreateTime(LocalDateTime.now());
        activityRuleEntity.setCreator(SecurityUtil.getName());
        activityRuleEntity.setUpdateTime(LocalDateTime.now());
        activityRuleEntity.setUpdater(SecurityUtil.getName());

        return activityRuleEntity;
    }

    public static ActivityRuleVO toActivityRuleVO(ActivityRuleEntity entity) {
        ActivityRuleVO activityRuleVO = new ActivityRuleVO();
        activityRuleVO.setId(entity.getId());
        activityRuleVO.setActivityId(entity.getActivityId());
        activityRuleVO.setRuleId(entity.getRuleId());
        activityRuleVO.setCreateTime(entity.getCreateTime());
        activityRuleVO.setCreator(entity.getCreator());
        activityRuleVO.setUpdateTime(entity.getUpdateTime());
        activityRuleVO.setUpdater(entity.getUpdater());

        return activityRuleVO;
    }
}
