package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.PrizeAddCmd;
import org.dog.luckyclient.dto.cmd.PrizeUpdateCmd;
import org.dog.luckyclient.dto.data.PrizeVO;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;

/**
 * @Author: Odin
 * @Date: 2023/5/5 12:59
 * @Description:
 */
public interface IPrizeService {

    PrizeVO add(PrizeAddCmd cmd);

    PrizeVO update(PrizeUpdateCmd cmd);

    IPage<PrizeVO> page(PrizeListByParamQuery query);

    PrizeVO one(Long id);
}
