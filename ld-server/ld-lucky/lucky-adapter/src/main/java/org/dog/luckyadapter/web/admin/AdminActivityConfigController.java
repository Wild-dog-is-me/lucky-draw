package org.dog.luckyadapter.web.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IActivityConfigService;
import org.dog.luckyclient.dto.cmd.ActivityConfigAddCmd;
import org.dog.luckyclient.dto.cmd.ActivityConfigUpdateCmd;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/13 15:19
 * @Description:
 */

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/activityConfig")
public class AdminActivityConfigController {

    private final IActivityConfigService activityConfigService;

    @PostMapping("/add")
    public ActivityConfigVO add(@Validated @RequestBody ActivityConfigAddCmd cmd){
        return activityConfigService.add(cmd);
    }

    @PostMapping("/update")
    public ActivityConfigVO update(@Validated @RequestBody ActivityConfigUpdateCmd cmd) {
        return activityConfigService.update(cmd);
    }

    @GetMapping("/one")
    public ActivityConfigVO one(@RequestParam("id") Long id) {
        return activityConfigService.one(id);
    }

}
