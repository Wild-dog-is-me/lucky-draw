package org.dog.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.api.IRuleService;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.dog.luckydomain.gateway.RuleGateway;
import org.dog.luckydomain.rule.RuleEntity;
import org.dog.luckyinfrastructure.convertor.RuleConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RuleDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.RuleMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:19
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RuleGatewayImpl implements RuleGateway {

    private final RuleMapper ruleMapper;

    @Override
    public RuleEntity save(RuleEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addRule(entity);
        }
        return updateRule(entity);
    }

    private RuleEntity updateRule(RuleEntity entity) {
        RuleDB ruleDB = RuleConvertor.toRuleDB(entity);
        AssertUtil.isTrue(ruleMapper.updateById(ruleDB) <= 0,
                LdExceptionEnum.UPDATE_ERROR.getDescription());
        return RuleConvertor.toEntity(ruleDB);
    }

    private RuleEntity addRule(RuleEntity entity) {
        RuleDB ruleDB = RuleConvertor.toRuleDB(entity);
        AssertUtil.isTrue(ruleMapper.insert(ruleDB) <= 0,
                LdExceptionEnum.ADD_ERROR.getDescription());
        return RuleConvertor.toEntity(ruleDB);
    }

    @Override
    public IPage<RuleEntity> page(RuleListByParamQuery query) {
        IPage<RuleDB> page = ruleMapper.page(new Page<RuleDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(RuleConvertor::toEntity);
    }

}
