package org.dog.luckyinfrastructure.convertor;

import org.dog.config.util.SecurityUtil;
import org.dog.luckydomain.prize.Inventory;
import org.dog.luckydomain.prize.PrizeEntity;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.PrizeDB;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/5 14:08
 * @Description:
 */
public class PrizeConvertor {
    public static PrizeDB toPrizeDB(PrizeEntity entity) {
        PrizeDB prizeDB = new PrizeDB();
        prizeDB.setId(entity.getId());
        prizeDB.setPrizeName(entity.getPrizeName());
        prizeDB.setInventory(entity.getInventory().getInventory());
        prizeDB.setMoney(new BigDecimal(entity.getMoney().toString()));
        prizeDB.setType(entity.getType());
        prizeDB.setCreateTime(LocalDateTime.now());
        prizeDB.setCreator(SecurityUtil.getName());
        prizeDB.setUpdateTime(LocalDateTime.now());
        prizeDB.setUpdater(SecurityUtil.getName());


        return prizeDB;
    }

    public static PrizeEntity toEntity(PrizeDB prizeDB) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(prizeDB.getId());
        prizeEntity.setPrizeName(prizeDB.getPrizeName());
        prizeEntity.setInventory(new Inventory(prizeDB.getInventory()));
        prizeEntity.setMoney(new BigDecimal(prizeDB.getMoney().toString()));
        prizeEntity.setType(prizeDB.getType());
        prizeEntity.setCreateTime(prizeDB.getCreateTime());
        prizeEntity.setCreator(prizeDB.getCreator());
        prizeEntity.setUpdateTime(prizeDB.getUpdateTime());
        prizeEntity.setUpdater(prizeDB.getUpdater());

        return prizeEntity;
    }
}
