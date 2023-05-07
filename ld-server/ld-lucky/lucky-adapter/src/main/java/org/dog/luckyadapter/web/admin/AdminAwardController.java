package org.dog.luckyadapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IAwardService;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;
import org.dog.luckyclient.dto.cmd.AwardUpdateCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/6 22:41
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/award")
public class AdminAwardController {

    private final IAwardService awardService;

    @PostMapping("/add")
    public AwardVO add(@Validated @RequestBody AwardAddCmd cmd) {
        return awardService.add(cmd);
    }

    @PostMapping("/update")
    public AwardVO update(@Validated @RequestBody AwardUpdateCmd cmd) {
        return awardService.update(cmd);
    }

    @GetMapping("/{id}")
    public AwardVO one(@PathVariable(name = "id") Long id) {
        return awardService.one(id);
    }

    @PostMapping("/page")
    public IPage<AwardVO> page(@RequestBody AwardListByParamQuery query) {
        return awardService.page(query);
    }


}
