package org.dog.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.prize.PrizeEntity;
import org.dog.luckyinfrastructure.convertor.PrizeConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.PrizeDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.PrizeMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/5 14:05
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class PrizeGatewayImpl implements PrizeGateway {

    private final PrizeMapper prizeMapper;

    @Override
    public PrizeEntity save(PrizeEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addPrize(entity);
        }
        return updatePrize(entity);
    }

    @Override
    public IPage<PrizeEntity> page(PrizeListByParamQuery query) {
        IPage<PrizeDB> page = prizeMapper.page(new Page<PrizeDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(PrizeConvertor::toEntity);
    }

    private PrizeEntity updatePrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConvertor.toPrizeDB(entity);
        AssertUtil.isTrue(prizeMapper.updateById(prizeDB) <= 0,
                LdExceptionEnum.UPDATE_ERROR.getDescription());
        return PrizeConvertor.toEntity(prizeDB);
    }

    private PrizeEntity addPrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConvertor.toPrizeDB(entity);

        AssertUtil.isTrue(prizeMapper.insert(prizeDB) <= 0,
                LdExceptionEnum.ADD_ERROR.getDescription());

        return PrizeConvertor.toEntity(prizeDB);
    }

}
