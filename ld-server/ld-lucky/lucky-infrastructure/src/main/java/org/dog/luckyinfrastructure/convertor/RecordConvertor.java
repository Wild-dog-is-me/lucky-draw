package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.record.RecordEntity;
import org.dog.luckydomain.record.RecordStatus;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RecordDB;

/**
 * @Author: Odin
 * @Date: 2023/5/21 12:01
 * @Description:
 */
public class RecordConvertor {
    public static RecordDB toRecordDB(RecordEntity entity) {
        RecordDB recordDB = new RecordDB();
        recordDB.setId(entity.getId());
        recordDB.setUserId(entity.getUserId());
        recordDB.setActivityId(entity.getActivityId());
        recordDB.setActivityName(entity.getActivityName());
        recordDB.setAwardId(entity.getAwardId());
        recordDB.setIsWinning(entity.getIsWinning());
        recordDB.setState(entity.getState().getState());
        recordDB.setCreateTime(entity.getCreateTime());
        recordDB.setCreator(entity.getCreator());
        recordDB.setUpdateTime(entity.getUpdateTime());
        recordDB.setUpdater(entity.getUpdater());

        return recordDB;
    }

    public static RecordEntity toEntity(RecordDB recordDB) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setAwardName(recordDB.getAwardName());
        recordEntity.setPrizeName(recordDB.getPrizeName());
        recordEntity.setId(recordDB.getId());
        recordEntity.setUserId(recordDB.getUserId());
        recordEntity.setActivityId(recordDB.getActivityId());
        recordEntity.setActivityName(recordDB.getActivityName());
        recordEntity.setAwardId(recordDB.getAwardId());
        recordEntity.setIsWinning(recordDB.getIsWinning());
        recordEntity.setState(new RecordStatus(recordDB.getState()));
        recordEntity.setCreateTime(recordDB.getCreateTime());
        recordEntity.setCreator(recordDB.getCreator());
        recordEntity.setUpdateTime(recordDB.getUpdateTime());
        recordEntity.setUpdater(recordDB.getUpdater());
        recordEntity.setPrizeType(recordDB.getPrizeType());

        return recordEntity;
    }
}
