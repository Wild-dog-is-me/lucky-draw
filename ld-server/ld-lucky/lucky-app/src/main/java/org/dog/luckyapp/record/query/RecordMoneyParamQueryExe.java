package org.dog.luckyapp.record.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckydomain.gateway.RecordGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: Odin
 * @Date: 2023/5/21 16:59
 * @Description:
 */
@Slf4j
@Component
@AllArgsConstructor
public class RecordMoneyParamQueryExe {
    private final RecordGateway recordGateway;

    public BigDecimal execute(Long recordId) {
        return recordGateway.getPrizeMoneyByRecordId(recordId);
    }
}
