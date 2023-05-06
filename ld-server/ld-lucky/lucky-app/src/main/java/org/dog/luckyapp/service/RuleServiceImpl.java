package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.service.rule.command.RuleAddCmdExe;
import org.dog.luckyapp.service.rule.command.RuleUpdateCmdExe;
import org.dog.luckyapp.service.rule.query.RuleListByParamQueryExe;
import org.dog.luckyclient.api.IRuleService;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:30
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RuleServiceImpl implements IRuleService {

    private final RuleAddCmdExe ruleAddCmdExe;
    private final RuleUpdateCmdExe ruleUpdateCmdExe;
    private final RuleListByParamQueryExe ruleListByParamQueryExe;

    @Override
    public RuleVO add(RuleAddCmd cmd) {
        return ruleAddCmdExe.execute(cmd);
    }

    @Override
    public RuleVO update(RuleUpdateCmd cmd) {
        return ruleUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<RuleVO> page(RuleListByParamQuery query) {
        return ruleListByParamQueryExe.execute(query);
    }

    @Override
    public RuleVO one(Long id) {
        final var query = new RuleListByParamQuery();
        query.setId(id);
        IPage<RuleVO> page = page(query);
        if (CollUtil.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }
}
