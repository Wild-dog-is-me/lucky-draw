package org.dog.luckyapp.service.prize.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.PrizeAssembler;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.prize.PrizeEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 00:11
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class PrizeListByParamQueryExe {

    private final PrizeGateway prizeGateway;

    public IPage<PrizeVO> execute(PrizeListByParamQuery query) {
        IPage<PrizeEntity> page = prizeGateway.page(query);
        return page.convert(PrizeAssembler::toPrizeVO);
    }
}
