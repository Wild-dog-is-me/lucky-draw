package org.dog.luckyapp.record.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.RecordAssembler;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckydomain.gateway.RecordGateway;
import org.dog.luckydomain.record.RecordEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/21 16:58
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RecordListByParamQueryExe {

    private final RecordGateway recordGateway;

    public IPage<RecordVO> execute(RecordListByParamQuery query) {
        IPage<RecordEntity> page = recordGateway.page(query);
        return page.convert(RecordAssembler::toRecordVO);
    }
}
