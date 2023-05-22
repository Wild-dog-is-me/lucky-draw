package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckydomain.record.RecordEntity;

import java.math.BigDecimal;

/**
 * @Author: Odin
 * @Date: 2023/5/21 12:23
 * @Description:
 */
public interface RecordGateway {

    RecordEntity save(RecordEntity entity);

    IPage<RecordEntity> page(RecordListByParamQuery query);

    Boolean updateStatus(Long id, Integer status);

    BigDecimal getPrizeMoneyByRecordId(Long recordId);

}
