package org.dog.luckyapp.assembler;

import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.dto.cmd.PrizeAddCmd;
import org.dog.luckyclient.dto.cmd.PrizeUpdateCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckydomain.prize.Inventory;
import org.dog.luckydomain.prize.PrizeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/5 13:31
 * @Description:
 */
public class PrizeAssembler {
    public static PrizeEntity toAddEntity(PrizeAddCmd cmd) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setPrizeName(cmd.getPrizeName());
        prizeEntity.setInventory(new Inventory(cmd.getInventory()));
        prizeEntity.setMoney(new BigDecimal(cmd.getMoney().toString()));
        prizeEntity.setType(cmd.getType());
        prizeEntity.setCreateTime(LocalDateTime.now());
        prizeEntity.setCreator(SecurityUtil.getName());
        prizeEntity.setUpdateTime(LocalDateTime.now());
        prizeEntity.setUpdater(SecurityUtil.getName());

        return prizeEntity;
    }

    public static PrizeVO toPrizeVO(PrizeEntity prizeEntity) {
        PrizeVO prizeVO = new PrizeVO();
        prizeVO.setId(prizeEntity.getId());
        prizeVO.setPrizeName(prizeEntity.getPrizeName());
        prizeVO.setInventory(prizeEntity.getInventory().getInventory());
        prizeVO.setMoney(new BigDecimal(prizeEntity.getMoney().toString()));
        prizeVO.setType(prizeEntity.getType());
        prizeVO.setCreateTime(prizeEntity.getCreateTime());
        prizeVO.setCreator(prizeEntity.getCreator());
        prizeVO.setUpdateTime(prizeEntity.getUpdateTime());
        prizeVO.setUpdater(prizeEntity.getUpdater());

        return prizeVO;
    }

    public static PrizeEntity toUpdateEntity(PrizeUpdateCmd cmd) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(cmd.getId());
        prizeEntity.setPrizeName(cmd.getPrizeName());
        prizeEntity.setInventory(new Inventory(cmd.getInventory()));
        prizeEntity.setMoney(new BigDecimal(cmd.getMoney().toString()));
        prizeEntity.setType(cmd.getType());
        prizeEntity.setUpdateTime(LocalDateTime.now());
        prizeEntity.setUpdater(SecurityUtil.getName());

        return prizeEntity;
    }
}

