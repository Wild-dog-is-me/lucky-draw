package org.dog.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.LdExceptionEnum;
import org.dog.config.exception.LdException;
import org.dog.config.util.AssertUtil;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.gateway.ActivityGateway;
import org.dog.luckyinfrastructure.convertor.ActivityConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.ActivityMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:41
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityGatewayImpl implements ActivityGateway {

    private final ActivityMapper activityMapper;


    @Override
    public ActivityEntity save(ActivityEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addActivity(entity);
        }
        return updateActivity(entity);
    }

    private ActivityEntity updateActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConvertor.toActivityDB(entity);
        AssertUtil.isTrue(activityMapper.insert(activityDB)<=0,
                LdExceptionEnum.ADD_ERROR.getDescription());
        return ActivityConvertor.toEntity(activityDB);
    }

    private ActivityEntity addActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConvertor.toActivityDB(entity);
        AssertUtil.isTrue(activityMapper.updateById(activityDB) <= 0,
                LdExceptionEnum.UPDATE_ERROR.getDescription());
        return ActivityConvertor.toEntity(activityDB);
    }

    @Override
    public IPage<ActivityEntity> page(ActivityListByParamQuery query) {
        IPage<ActivityDB> page = activityMapper.page(new Page<ActivityDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(ActivityConvertor::toEntity);
    }

}
