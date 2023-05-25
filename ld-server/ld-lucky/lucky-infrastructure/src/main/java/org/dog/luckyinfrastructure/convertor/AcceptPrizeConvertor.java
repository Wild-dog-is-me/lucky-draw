package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.acceptprize.AcceptPrizeEntity;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:48
 * @Description:
 */
public class AcceptPrizeConvertor {
    public static AcceptPrizeDB toAcceptPrizeDB(AcceptPrizeEntity toEntity) {
        AcceptPrizeDB acceptPrizeDB = new AcceptPrizeDB();
        acceptPrizeDB.setRecordId(toEntity.getRecordId());
        acceptPrizeDB.setPhone(toEntity.getPhone());
        acceptPrizeDB.setAddress(toEntity.getAddress());
        acceptPrizeDB.setCreateTime(toEntity.getCreateTime());
        acceptPrizeDB.setCreator(toEntity.getCreator());
        acceptPrizeDB.setUpdateTime(toEntity.getUpdateTime());
        acceptPrizeDB.setUpdater(toEntity.getUpdater());

        return acceptPrizeDB;
    }

    public static AcceptPrizeEntity toEntity(AcceptPrizeDB acceptPrizeDB) {
        AcceptPrizeEntity entity = new AcceptPrizeEntity();
        entity.setId(acceptPrizeDB.getId());
        entity.setRecordId(acceptPrizeDB.getRecordId());
        entity.setPhone(acceptPrizeDB.getPhone());
        entity.setAddress(acceptPrizeDB.getAddress());
        entity.setCreateTime(acceptPrizeDB.getCreateTime());
        entity.setCreator(acceptPrizeDB.getCreator());
        entity.setUpdateTime(acceptPrizeDB.getUpdateTime());
        entity.setUpdater(acceptPrizeDB.getUpdater());

        return entity;
    }
}
