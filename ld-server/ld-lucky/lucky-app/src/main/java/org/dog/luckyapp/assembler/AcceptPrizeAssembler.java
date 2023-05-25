package org.dog.luckyapp.assembler;

import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.dto.cmd.AcceptPrizeAddCmd;
import org.dog.luckyclient.dto.data.AcceptPrizeVO;
import org.dog.luckydomain.acceptprize.AcceptPrizeEntity;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:58
 * @Description:
 */
public class AcceptPrizeAssembler {

    public static AcceptPrizeEntity toEntity(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity entity = new AcceptPrizeEntity();
        entity.setRecordId(cmd.getRecordId());
        entity.setPhone(cmd.getPhone());
        entity.setAddress(cmd.getAddress());
        entity.setCreateTime(LocalDateTime.now());
        entity.setCreator(SecurityUtil.getUserName());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdater(SecurityUtil.getUserName());

        return entity;
    }

    public static AcceptPrizeVO toAcceptPrizeVO(AcceptPrizeEntity entity) {
        AcceptPrizeVO acceptPrizeVO = new AcceptPrizeVO();
        acceptPrizeVO.setId(entity.getId());
        acceptPrizeVO.setPhone(entity.getPhone());
        acceptPrizeVO.setAddress(entity.getAddress());
        acceptPrizeVO.setCreateTime(entity.getCreateTime());

        return acceptPrizeVO;
    }
}
