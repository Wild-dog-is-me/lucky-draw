package org.dog.luckyapp.activity.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.ActivityAssembler;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.dog.luckydomain.activity.ActivityEntity;
import org.dog.luckydomain.gateway.ActivityGateway;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:34
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityListByParamQueryExe {

    private final ActivityGateway activityGateway;

    public IPage<ActivityVO> execute(ActivityListByParamQuery query) {
        IPage<ActivityEntity> page = activityGateway.page(query);

        return page.convert(ActivityAssembler::toActivityVO);
    }
}
