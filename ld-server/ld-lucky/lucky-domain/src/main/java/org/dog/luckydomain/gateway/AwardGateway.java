package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.dog.luckydomain.award.AwardEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:40
 * @Description:
 */


public interface AwardGateway {

    AwardEntity save(AwardEntity entity);

    IPage<AwardEntity> page(AwardListByParamQuery query);
}
