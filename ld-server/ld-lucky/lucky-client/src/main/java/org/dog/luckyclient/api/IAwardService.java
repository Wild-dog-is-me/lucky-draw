package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;
import org.dog.luckyclient.dto.cmd.AwardUpdateCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:08
 * @Description:
 */
public interface IAwardService {

    AwardVO add(AwardAddCmd cmd);

    AwardVO update(AwardUpdateCmd cmd);

    AwardVO one(Long id);

    IPage<AwardVO> page(AwardListByParamQuery query);
}
