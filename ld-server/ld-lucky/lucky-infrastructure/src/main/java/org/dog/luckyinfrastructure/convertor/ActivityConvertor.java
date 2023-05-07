package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.activity.ActivityTime;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityDB;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:12
 * @Description:
 */
public class ActivityConvertor {
    public static ActivityDB toActivityDB(ActivityEntity entity) {
        ActivityDB activityDB = new ActivityDB();
        activityDB.setId(entity.getId());
        activityDB.setActivityName(entity.getActivityName());
        activityDB.setStartTime(entity.getActivityTime().getStartTime());
        activityDB.setEndTime(entity.getActivityTime().getEndTime());
        activityDB.setDescribe(entity.getDescribe());
        activityDB.setCreateTime(entity.getCreateTime());
        activityDB.setCreator(entity.getCreator());
        activityDB.setUpdateTime(entity.getUpdateTime());
        activityDB.setUpdater(entity.getUpdater());

        return activityDB;
    }

    public static ActivityEntity toEntity(ActivityDB activityDB) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(activityDB.getId());
        activityEntity.setActivityName(activityDB.getActivityName());
        activityEntity.setActivityTime(new ActivityTime(activityDB.getStartTime(), activityDB.getEndTime()));
        activityEntity.setDescribe(activityDB.getDescribe());
        activityEntity.setCreateTime(activityDB.getCreateTime());
        activityEntity.setCreator(activityDB.getCreator());
        activityEntity.setUpdateTime(activityDB.getUpdateTime());
        activityEntity.setUpdater(activityDB.getUpdater());


        return activityEntity;
    }
}
