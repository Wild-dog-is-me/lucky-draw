package org.dog.luckyapp.rule.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.RuleAssembler;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckydomain.gateway.RuleGateway;
import org.dog.luckydomain.rule.RuleEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:08
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class RuleAddCmdExe {

    private final RuleGateway ruleGateway;

    public RuleVO execute(RuleAddCmd cmd) {
        RuleEntity entity = ruleGateway.save(RuleAssembler.toAddEntity(cmd));
        return RuleAssembler.toRuleVO(entity);
    }
}
