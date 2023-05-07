package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.dog.luckydomain.activity.ActivityEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:37
 * @Description:
 */
public interface ActivityGateway {

    ActivityEntity save(ActivityEntity entity);

    IPage<ActivityEntity> page(ActivityListByParamQuery query);
}
