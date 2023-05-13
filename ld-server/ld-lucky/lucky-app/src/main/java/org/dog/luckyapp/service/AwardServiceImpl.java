package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.award.command.AwardAddCmdExe;
import org.dog.luckyapp.award.command.AwardUpdateCmdExe;
import org.dog.luckyapp.award.query.AwardListByParamQueryExe;
import org.dog.luckyclient.api.IAwardService;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;
import org.dog.luckyclient.dto.cmd.AwardUpdateCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:24
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class AwardServiceImpl implements IAwardService {

    private final AwardAddCmdExe awardAddCmdExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;

    @Override
    public AwardVO add(AwardAddCmd cmd) {
        return awardAddCmdExe.execute(cmd);
    }

    @Override
    public AwardVO update(AwardUpdateCmd cmd) {
        return awardUpdateCmdExe.execute(cmd);
    }

    @Override
    public AwardVO one(Long id) {
        final var query = new AwardListByParamQuery();
        query.setId(id);
        IPage<AwardVO> page = page(query);
        if (CollUtil.isEmpty(page.getRecords())) {
            return null;
        }
        return page.getRecords().get(0);
    }

    @Override
    public IPage<AwardVO> page(AwardListByParamQuery query) {
        return awardListByParamQueryExe.execute(query);
    }

}
