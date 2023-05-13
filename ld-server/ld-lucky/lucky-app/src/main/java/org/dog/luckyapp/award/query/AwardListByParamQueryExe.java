package org.dog.luckyapp.award.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:32
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardListByParamQueryExe {

    private final AwardGateway awardGateway;

    public IPage<AwardVO> execute(AwardListByParamQuery query) {
        IPage<AwardEntity> page = awardGateway.page(query);

        return page.convert(AwardAssembler::toAwardVO);
    }
}
