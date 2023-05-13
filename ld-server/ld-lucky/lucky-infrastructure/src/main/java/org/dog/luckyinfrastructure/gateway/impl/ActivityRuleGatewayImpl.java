package org.dog.luckyinfrastructure.gateway.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.dog.luckydomain.activityrule.ActivityRuleEntity;
import org.dog.luckydomain.gateway.ActivityRuleGateway;
import org.dog.luckyinfrastructure.convertor.ActivityRuleConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityRuleDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.ActivityRuleMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 23:34
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleGatewayImpl implements ActivityRuleGateway {

    private final ActivityRuleMapper activityRuleMapper;

    @Override
    public ActivityRuleEntity save(ActivityRuleEntity entity) {
        ActivityRuleDB activityRuleDB = ActivityRuleConvertor.toActivityRuleDB(entity);

        AssertUtil.isTrue(activityRuleMapper.insert(activityRuleDB) <= 0,
                LdExceptionEnum.ADD_ERROR.getDescription());
        return ActivityRuleConvertor.toEntity(activityRuleDB);
    }

    @Override
    public List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query) {
        List<ActivityRuleDB> list = activityRuleMapper.list(query);
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return new Page<ActivityRuleDB>()
                .setRecords(list)
                .convert(ActivityRuleConvertor::toEntity)
                .getRecords();
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        activityRuleMapper.deleteByActivityId(activityId);
    }

}
