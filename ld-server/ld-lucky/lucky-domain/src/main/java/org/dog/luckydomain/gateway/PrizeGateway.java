package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.config.exception.LdException;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.dog.luckydomain.prize.PrizeEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/5 13:30
 * @Description:
 */
public interface PrizeGateway {

    PrizeEntity save(PrizeEntity entity);

    IPage<PrizeEntity> page(PrizeListByParamQuery query);

    default PrizeEntity one(Long id) {
        final var query = new PrizeListByParamQuery();
        query.setId(id);
        PrizeEntity prizeEntity = null;
        try {
            prizeEntity = page(query).getRecords().get(0);
        } catch (Exception e) {
            //错误处理
            throw new LdException(String.format("奖品id：%s，不存在！", id));
        }
        return prizeEntity;
    }

}
