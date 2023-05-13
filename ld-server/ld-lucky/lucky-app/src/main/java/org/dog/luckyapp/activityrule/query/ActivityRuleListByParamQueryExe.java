package org.dog.luckyapp.activityrule.query;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.ActivityRuleAssembler;
import org.dog.luckyclient.dto.data.ActivityRuleVO;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.dog.luckydomain.activityrule.ActivityRuleEntity;
import org.dog.luckydomain.gateway.ActivityRuleGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 22:58
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleListByParamQueryExe {

    private final ActivityRuleGateway activityRuleGateway;

    public List<ActivityRuleVO> execute(ActivityRuleListByParamQuery query) {
        List<ActivityRuleEntity> list = activityRuleGateway.list(query);
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }

        return new Page<ActivityRuleEntity>()
                .setRecords(list)
                .convert(ActivityRuleAssembler::toActivityRuleVO)
                .getRecords();
    }
}
