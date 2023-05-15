package org.dog.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IActivityConfigService;
import org.dog.luckyclient.api.IActivityService;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.DrawResultVO;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/7 23:23
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/activity")
public class ActivityController {

    private final IActivityService activityService;
    private final IActivityConfigService activityConfigService;

    @PostMapping("/page")
    public IPage<ActivityVO> page(@RequestBody ActivityListByParamQuery query) {
        return activityService.page(query);
    }

    @GetMapping("/one")
    public ActivityConfigVO one(@RequestParam("id") Long id) {
        return activityConfigService.one(id);
    }

    @GetMapping("/draw")
    public DrawResultVO draw(@RequestParam("activityId") Long activityId) {
        return activityService.draw(activityId);
    }
}
