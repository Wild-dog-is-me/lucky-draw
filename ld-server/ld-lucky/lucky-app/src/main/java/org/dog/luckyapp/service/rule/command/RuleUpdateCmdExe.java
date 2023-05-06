package org.dog.luckyapp.service.rule.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.RuleAssembler;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
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
public class RuleUpdateCmdExe {

    private final RuleGateway ruleGateway;

    public RuleVO execute(RuleUpdateCmd cmd) {
        RuleEntity entity = ruleGateway.save(RuleAssembler.toUpdateEntity(cmd));
        return RuleAssembler.toRuleVO(entity);
    }

}
