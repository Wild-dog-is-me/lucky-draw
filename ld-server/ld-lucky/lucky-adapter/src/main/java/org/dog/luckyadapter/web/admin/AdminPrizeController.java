package org.dog.luckyadapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IPrizeService;
import org.dog.luckyclient.dto.cmd.PrizeAddCmd;
import org.dog.luckyclient.dto.cmd.PrizeUpdateCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Odin
 * @Date: 2023/5/6 00:31
 * @Description:
 */

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/prize")
public class AdminPrizeController {

    private final IPrizeService prizeService;

    @PostMapping("/add")
    public PrizeVO add(@Validated @RequestBody PrizeAddCmd cmd) {
        return prizeService.add(cmd);
    }

    @PostMapping("/update")
    public PrizeVO update(@Validated @RequestBody PrizeUpdateCmd cmd) {
        return prizeService.update(cmd);
    }

    @PostMapping("/page")
    public IPage<PrizeVO> page(@RequestBody PrizeListByParamQuery query) {
        return prizeService.page(query);
    }

    @PostMapping("/{id}")
    public PrizeVO one(@PathVariable(name = "id") Long id) {
        return prizeService.one(id);
    }
}
