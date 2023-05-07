package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.service.prize.command.PrizeAddCmdExe;
import org.dog.luckyapp.service.prize.command.PrizeUpdateCmdExe;
import org.dog.luckyapp.service.prize.query.PrizeListByParamQueryExe;
import org.dog.luckyclient.api.IPrizeService;
import org.dog.luckyclient.dto.cmd.PrizeAddCmd;
import org.dog.luckyclient.dto.cmd.PrizeUpdateCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/5/5 13:14
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class PrizeServiceImpl implements IPrizeService {

    private final PrizeAddCmdExe prizeAddCmdExe;
    private final PrizeUpdateCmdExe prizeUpdateCmdExe;
    private final PrizeListByParamQueryExe prizeListByParamQueryExe;

    @Override
    public PrizeVO add(PrizeAddCmd cmd) {
        return prizeAddCmdExe.excute(cmd);
    }

    @Override
    public PrizeVO update(PrizeUpdateCmd cmd) {
        return prizeUpdateCmdExe.excute(cmd);
    }

    @Override
    public IPage<PrizeVO> page(PrizeListByParamQuery query) {
        return prizeListByParamQueryExe.execute(query);
    }

    @Override
    public PrizeVO one(Long id) {
        final var query = new PrizeListByParamQuery();
        query.setId(id);
        IPage<PrizeVO> page = page(query);
        if (CollUtil.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }

}
