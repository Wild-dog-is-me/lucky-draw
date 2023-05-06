package org.dog.luckyapp.assembler;

import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckydomain.rule.MinNumber;
import org.dog.luckydomain.rule.RuleEntity;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:02
 * @Description:
 */
public class RuleAssembler {
    public static RuleEntity toAddEntity(RuleAddCmd cmd) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setCreateTime(LocalDateTime.now());
        ruleEntity.setCreator(SecurityUtil.getName());
        ruleEntity.setUpdateTime(LocalDateTime.now());
        ruleEntity.setUpdater(SecurityUtil.getName());

        return ruleEntity;
    }

    public static RuleVO toRuleVO(RuleEntity entity) {
        RuleVO ruleVO = new RuleVO();
        ruleVO.setId(entity.getId());
        ruleVO.setRuleName(entity.getRuleName());
        ruleVO.setMaxJoinNumber(entity.getMaxJoinNumber().getNumber());
        ruleVO.setMaxWinningNumber(entity.getMaxWinningNumber().getNumber());
        ruleVO.setCreateTime(entity.getCreateTime());
        ruleVO.setCreator(entity.getCreator());
        ruleVO.setUpdateTime(entity.getUpdateTime());
        ruleVO.setUpdater(entity.getUpdater());

        return ruleVO;
    }

    public static RuleEntity toUpdateEntity(RuleUpdateCmd cmd) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId(cmd.getId());
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setUpdateTime(LocalDateTime.now());
        ruleEntity.setUpdater(SecurityUtil.getName());

        return ruleEntity;
    }
}
