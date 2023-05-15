package org.dog.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckyinfrastructure.convertor.AwardConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AwardDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.AwardMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:43
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardGatewayImpl implements AwardGateway {

    private final AwardMapper awardMapper;

    @Override
    public AwardEntity save(AwardEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addAward(entity);
        }
        return updateAward(entity);
    }

    private AwardEntity addAward(AwardEntity entity) {
        AwardDB awardDB = AwardConvertor.toAwardDB(entity);
        AssertUtil.isTrue(awardMapper.insert(awardDB) <= 0,
                LdExceptionEnum.ADD_ERROR.getDescription());
        return AwardConvertor.toEntity(awardDB);
    }


    private AwardEntity updateAward(AwardEntity entity) {
        AwardDB awardDB = AwardConvertor.toAwardDB(entity);
        AssertUtil.isTrue(awardMapper.updateById(awardDB) <= 0,
                LdExceptionEnum.UPDATE_ERROR.getDescription());
        return AwardConvertor.toEntity(awardDB);
    }

    @Override
    public IPage<AwardEntity> page(AwardListByParamQuery query) {
        IPage<AwardDB> page = awardMapper.page(new Page<AwardDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(AwardConvertor::toEntity);
    }

    @Override
    public int deductionAwardNumber(Long awardId, Integer number) {
        return awardMapper.deductionAwardNumber(awardId, number);
    }

}
