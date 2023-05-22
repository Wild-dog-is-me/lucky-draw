package org.dog.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.api.IRecordService;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/21 17:43
 * @Description:
 */

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/record")
public class RecordController {

    private final IRecordService recordServer;

    @PostMapping("/page")
    public IPage<RecordVO> page(@RequestBody RecordListByParamQuery query) {
        query.setUserId(SecurityUtil.getUserId());
        return recordServer.page(query);
    }

    @GetMapping("/prizeType")
    public Integer prizeType(@RequestParam("recordId") Long recordId) {
        return recordServer.prizeType(recordId);
    }

    @GetMapping("/updateStatusTo4")
    public Boolean updateStatusTo4(RecordUpdateStatusCmd cmd) {
        cmd.setState(4);
        return recordServer.update(cmd);
    }


    @GetMapping("/exchangeMoney")
    public Boolean exchangeMoney(@RequestParam("recordId") Long recordId) {
        return recordServer.exchangeMoney(recordId);
    }

}
