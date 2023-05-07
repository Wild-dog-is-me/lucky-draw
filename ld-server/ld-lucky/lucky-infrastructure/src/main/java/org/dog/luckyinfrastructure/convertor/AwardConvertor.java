package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.award.AwardNumber;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AwardDB;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:26
 * @Description:
 */
public class AwardConvertor {
    public static AwardDB toAwardDB(AwardEntity entity) {
        AwardDB awardDB = new AwardDB();
        awardDB.setId(entity.getId());
        awardDB.setPrizeId(entity.getPrizeId());
        awardDB.setActivityId(entity.getActivityId());
        awardDB.setNumber(entity.getNumber().getNumber());
        awardDB.setAwardName(entity.getAwardName());
        awardDB.setProbability(entity.getProbability());
        awardDB.setCreateTime(entity.getCreateTime());
        awardDB.setCreator(entity.getCreator());
        awardDB.setUpdateTime(entity.getUpdateTime());
        awardDB.setUpdater(entity.getUpdater());

        return awardDB;
    }

    public static AwardEntity toEntity(AwardDB awardDB) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setId(awardDB.getId());
        awardEntity.setPrizeId(awardDB.getPrizeId());
        awardEntity.setActivityId(awardDB.getActivityId());
        awardEntity.setNumber(new AwardNumber(awardDB.getNumber()));
        awardEntity.setAwardName(awardDB.getAwardName());
        awardEntity.setProbability(awardDB.getProbability());
        awardEntity.setCreateTime(awardDB.getCreateTime());
        awardEntity.setCreator(awardDB.getCreator());
        awardEntity.setUpdateTime(awardDB.getUpdateTime());
        awardEntity.setUpdater(awardDB.getUpdater());
//        awardEntity.setPrizeName(awardDB.getPrizeName());

        return awardEntity;
    }
}
