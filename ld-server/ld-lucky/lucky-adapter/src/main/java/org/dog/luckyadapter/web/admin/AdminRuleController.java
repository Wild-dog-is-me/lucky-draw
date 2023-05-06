package org.dog.luckyadapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IRuleService;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/6 11:08
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/rule")
public class AdminRuleController {

    private final IRuleService ruleService;

    @PostMapping("/add")
    public RuleVO add(@Validated @RequestBody RuleAddCmd cmd) {
        return ruleService.add(cmd);
    }

    @PostMapping("/update")
    public RuleVO update(@Validated @RequestBody RuleUpdateCmd cmd) {
        return ruleService.update(cmd);
    }

    @PostMapping("/page")
    public IPage<RuleVO> page(@RequestBody RuleListByParamQuery query) {
        return ruleService.page(query);
    }

    @GetMapping("/{id}")
    public RuleVO one(@PathVariable(name = "id") Long id) {
        return ruleService.one(id);
    }
}
