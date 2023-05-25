package org.dog.luckyadapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.api.IRecordService;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Odin
 * @Date: 2023/5/26 00:25
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/record")
public class AdminRecordController {

    private final IRecordService recordServer;

    @PostMapping("/page")
    public IPage<RecordVO> page(@RequestBody RecordListByParamQuery query) {
        query.setUserId(SecurityUtil.getUserId());
        return recordServer.page(query);
    }

    @GetMapping("/updateStatusTo3")
    public Boolean updateStatusTo3(@RequestBody RecordUpdateStatusCmd cmd) {
        cmd.setState(3);
        return recordServer.update(cmd);
    }

}
