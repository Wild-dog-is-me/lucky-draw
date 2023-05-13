package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityRuleDB;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 20:10
 * @Description:
 */
public interface ActivityRuleMapper extends BaseMapper<ActivityRuleDB> {

    List<ActivityRuleDB> list(ActivityRuleListByParamQuery query);

    void deleteByActivityId(Long activityId);
}
