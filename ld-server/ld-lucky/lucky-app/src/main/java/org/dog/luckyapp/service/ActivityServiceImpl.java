package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.activity.cmd.ActivityAddCmdExe;
import org.dog.luckyapp.activity.cmd.ActivityUpdateCmdExe;
import org.dog.luckyapp.activity.query.ActivityListByParamQueryExe;
import org.dog.luckyclient.api.IActivityService;
import org.dog.luckyclient.dto.cmd.ActivityAddCmd;
import org.dog.luckyclient.dto.cmd.ActivityUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/7 22:23
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class ActivityServiceImpl implements IActivityService {

    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityListByParamQueryExe activityListByParamQueryExe;

    @Override
    public ActivityVO add(ActivityAddCmd cmd) {
        return activityAddCmdExe.execute(cmd);
    }

    @Override
    public ActivityVO update(ActivityUpdateCmd cmd) {
        return activityUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<ActivityVO> page(ActivityListByParamQuery query) {
        return activityListByParamQueryExe.execute(query);
    }

    @Override
    public ActivityVO one(Long id) {
        final var query = new ActivityListByParamQuery();
        query.setId(id);
        IPage<ActivityVO> page = page(query);
        if (CollUtil.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }

}
