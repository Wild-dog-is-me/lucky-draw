package org.dog.luckyinfrastructure.gateway.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.util.AssertUtil;
import org.dog.luckydomain.acceptprize.AcceptPrizeEntity;
import org.dog.luckydomain.gateway.AcceptPrizeGateway;
import org.dog.luckyinfrastructure.convertor.AcceptPrizeConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.AcceptPrizeMapper;
import org.dog.luckyinfrastructure.gateway.impl.mapper.RecordMapper;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:50
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AcceptPrizeGatewayImpl implements AcceptPrizeGateway {

    private final AcceptPrizeMapper acceptPrizeMapper;
    private final RecordMapper recordMapper;


    @Override
    public AcceptPrizeEntity save(AcceptPrizeEntity toEntity) {
        AcceptPrizeDB acceptPrizeDB = AcceptPrizeConvertor.toAcceptPrizeDB(toEntity);
        AssertUtil.isTrue(acceptPrizeMapper.insert(acceptPrizeDB) <= 0, LdExceptionEnum.ADD_ERROR.getDescription());
        return AcceptPrizeConvertor.toEntity(acceptPrizeDB);
    }

    @Override
    public AcceptPrizeEntity one(Long recordId) {
        AcceptPrizeDB acceptPrizeDB = acceptPrizeMapper.getByRecordId(recordId);
        return AcceptPrizeConvertor.toEntity(acceptPrizeDB);
    }

}
