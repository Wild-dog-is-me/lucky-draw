package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.RuleAddCmd;
import org.dog.luckyclient.dto.cmd.RuleUpdateCmd;
import org.dog.luckyclient.dto.data.RuleVO;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:14
 * @Description:
 */
public interface IRuleService {

    RuleVO add(RuleAddCmd cmd);

    RuleVO update(RuleUpdateCmd cmd);

    IPage<RuleVO> page(RuleListByParamQuery query);

    RuleVO one(Long id);

}
