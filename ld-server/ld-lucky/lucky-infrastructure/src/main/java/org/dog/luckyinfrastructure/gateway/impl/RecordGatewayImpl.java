package org.dog.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.exception.LdException;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckydomain.gateway.RecordGateway;
import org.dog.luckydomain.record.RecordEntity;
import org.dog.luckyinfrastructure.convertor.RecordConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RecordDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.RecordMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: Odin
 * @Date: 2023/5/21 00:01
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RecordGatewayImpl implements RecordGateway {

    private final RecordMapper recordMapper;

    @Override
    public RecordEntity save(RecordEntity entity) {
        RecordDB recordDB = RecordConvertor.toRecordDB(entity);
        AssertUtil.isTrue(recordMapper.insert(recordDB) != 1, LdExceptionEnum.ADD_ERROR.getDescription());
        return RecordConvertor.toEntity(recordDB);
    }

    @Override
    public IPage<RecordEntity> page(RecordListByParamQuery query) {
        IPage<RecordDB> page = recordMapper.page(new Page<RecordDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(RecordConvertor::toEntity);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        return recordMapper.updateStatus(id, status) == 1;
    }

    @Override
    public BigDecimal getPrizeMoneyByRecordId(Long recordId) {
        return recordMapper.getPrizeMoneyByRecordId(recordId);
    }

}
