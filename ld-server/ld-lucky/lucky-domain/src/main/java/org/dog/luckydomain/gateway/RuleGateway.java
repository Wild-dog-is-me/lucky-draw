package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.dog.luckydomain.rule.RuleEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:40
 * @Description:
 */
public interface RuleGateway {

    RuleEntity save(RuleEntity entity);

    IPage<RuleEntity> page(RuleListByParamQuery query);
}
