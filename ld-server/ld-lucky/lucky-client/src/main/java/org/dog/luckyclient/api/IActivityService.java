package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.ActivityAddCmd;
import org.dog.luckyclient.dto.cmd.ActivityUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.DrawResultVO;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;

/**
 * @Author: Odin
 * @Date: 2023/5/7 20:18
 * @Description:
 */
public interface IActivityService {

    ActivityVO add(ActivityAddCmd cmd);

    ActivityVO update(ActivityUpdateCmd cmd);

    IPage<ActivityVO> page(ActivityListByParamQuery query);

    ActivityVO one(Long id);

    DrawResultVO draw(Long activityId);
}
