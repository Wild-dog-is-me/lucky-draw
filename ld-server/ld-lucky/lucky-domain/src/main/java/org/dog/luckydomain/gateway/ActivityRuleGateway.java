package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.dog.luckydomain.activityrule.ActivityRuleEntity;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 23:29
 * @Description:
 */
public interface ActivityRuleGateway {

    ActivityRuleEntity save(ActivityRuleEntity entity);

    List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query);

    void deleteByActivityId(Long activityId);

}
