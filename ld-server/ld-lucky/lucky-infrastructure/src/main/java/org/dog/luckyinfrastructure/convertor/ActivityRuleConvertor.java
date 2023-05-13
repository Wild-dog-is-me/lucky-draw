package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.activityrule.ActivityRuleEntity;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityRuleDB;

/**
 * @Author: Odin
 * @Date: 2023/5/10 00:21
 * @Description:
 */
public class ActivityRuleConvertor {
    public static ActivityRuleDB toActivityRuleDB(ActivityRuleEntity entity) {
        ActivityRuleDB activityRuleDB = new ActivityRuleDB();
        activityRuleDB.setId(entity.getId());
        activityRuleDB.setActivityId(entity.getActivityId());
        activityRuleDB.setRuleId(entity.getRuleId());
        activityRuleDB.setCreateTime(entity.getCreateTime());
        activityRuleDB.setCreator(entity.getCreator());
        activityRuleDB.setUpdateTime(entity.getUpdateTime());
        activityRuleDB.setUpdater(entity.getUpdater());

        return activityRuleDB;
    }

    public static ActivityRuleEntity toEntity(ActivityRuleDB activityRuleDB) {
        ActivityRuleEntity activityRuleEntity = new ActivityRuleEntity();
        activityRuleEntity.setId(activityRuleDB.getId());
        activityRuleEntity.setActivityId(activityRuleDB.getActivityId());
        activityRuleEntity.setRuleId(activityRuleDB.getRuleId());
        activityRuleEntity.setCreateTime(activityRuleDB.getCreateTime());
        activityRuleEntity.setCreator(activityRuleDB.getCreator());
        activityRuleEntity.setUpdateTime(activityRuleDB.getUpdateTime());
        activityRuleEntity.setUpdater(activityRuleDB.getUpdater());

        return activityRuleEntity;
    }
}
