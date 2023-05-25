package org.dog.luckydomain.gateway;

import org.dog.luckydomain.acceptprize.AcceptPrizeEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:51
 * @Description:
 */
public interface AcceptPrizeGateway {

    AcceptPrizeEntity save(AcceptPrizeEntity toEntity);

    AcceptPrizeEntity one(Long recordId);
}
