package org.dog.luckyapp.assembler;

import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.dto.cmd.ActivityAddCmd;
import org.dog.luckyclient.dto.cmd.ActivityUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.activity.ActivityTime;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/7 23:03
 * @Description:
 */
public class ActivityAssembler {
    public static ActivityEntity toAddEntity(ActivityAddCmd cmd) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityName(cmd.getActivityName());
        activityEntity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        activityEntity.setDescribe(cmd.getDescribe());
        activityEntity.setCreateTime(LocalDateTime.now());
        activityEntity.setCreator(SecurityUtil.getName());
        activityEntity.setUpdateTime(LocalDateTime.now());
        activityEntity.setUpdater(SecurityUtil.getName());

        return activityEntity;
    }

    public static ActivityVO toActivityVO(ActivityEntity entity) {
        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(entity.getId());
        activityVO.setActivityName(entity.getActivityName());
        activityVO.setStartTime(entity.getActivityTime().getStartTime());
        activityVO.setEndTime(entity.getActivityTime().getEndTime());
        activityVO.setDescribe(entity.getDescribe());
        activityVO.setCreateTime(entity.getCreateTime());
        activityVO.setCreator(entity.getCreator());
        activityVO.setUpdateTime(entity.getUpdateTime());
        activityVO.setUpdater(entity.getUpdater());
        activityVO.setStatus(entity.getActivityTime().getStatus().getValue());

        return activityVO;
    }

    public static ActivityEntity toUpdateEntity(ActivityUpdateCmd cmd) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(cmd.getId());
        activityEntity.setActivityName(cmd.getActivityName());
        activityEntity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        activityEntity.setDescribe(cmd.getDescribe());
        activityEntity.setUpdateTime(LocalDateTime.now());
        activityEntity.setUpdater(SecurityUtil.getName());

        return activityEntity;
    }

    public static ActivityAddCmd toActivityAddCmd(ActivityVO activityVO) {
        ActivityAddCmd activityAddCmd = new ActivityAddCmd();
        activityAddCmd.setActivityName(activityVO.getActivityName());
        activityAddCmd.setStartTime(activityVO.getStartTime());
        activityAddCmd.setEndTime(activityVO.getEndTime());
        activityAddCmd.setDescribe(activityVO.getDescribe());

        return activityAddCmd;
    }
}

