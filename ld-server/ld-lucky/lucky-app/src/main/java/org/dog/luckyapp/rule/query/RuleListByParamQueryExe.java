package org.dog.luckyapp.rule.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.RuleAssembler;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.dog.luckydomain.gateway.RuleGateway;
import org.dog.luckydomain.rule.RuleEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:29
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RuleListByParamQueryExe {

    private final RuleGateway ruleGateway;

    public IPage<RuleVO> execute(RuleListByParamQuery query) {
        IPage<RuleEntity> page = ruleGateway.page(query);
        return page.convert(RuleAssembler::toRuleVO);
    }

}
