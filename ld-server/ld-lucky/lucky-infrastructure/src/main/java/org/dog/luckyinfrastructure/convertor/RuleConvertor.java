package org.dog.luckyinfrastructure.convertor;

import org.dog.luckydomain.rule.MinNumber;
import org.dog.luckydomain.rule.RuleEntity;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RuleDB;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:04
 * @Description:
 */
public class RuleConvertor {
    public static RuleDB toRuleDB(RuleEntity entity) {
        RuleDB ruleDB = new RuleDB();
        ruleDB.setId(entity.getId());
        ruleDB.setRuleName(entity.getRuleName());
        ruleDB.setMaxJoinNumber(entity.getMaxJoinNumber().getNumber());
        ruleDB.setMaxWinningNumber(entity.getMaxWinningNumber().getNumber());
        ruleDB.setCreateTime(entity.getCreateTime());
        ruleDB.setCreator(entity.getCreator());
        ruleDB.setUpdateTime(entity.getUpdateTime());
        ruleDB.setUpdater(entity.getUpdater());


        return ruleDB;
    }

    public static RuleEntity toEntity(RuleDB ruleDB) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setId(ruleDB.getId());
        ruleEntity.setRuleName(ruleDB.getRuleName());
        ruleEntity.setMaxJoinNumber(new MinNumber(ruleDB.getMaxJoinNumber()));
        ruleEntity.setMaxWinningNumber(new MinNumber(ruleDB.getMaxWinningNumber()));
        ruleEntity.setCreateTime(ruleDB.getCreateTime());
        ruleEntity.setCreator(ruleDB.getCreator());
        ruleEntity.setUpdateTime(ruleDB.getUpdateTime());
        ruleEntity.setUpdater(ruleDB.getUpdater());

        return ruleEntity;
    }
}

