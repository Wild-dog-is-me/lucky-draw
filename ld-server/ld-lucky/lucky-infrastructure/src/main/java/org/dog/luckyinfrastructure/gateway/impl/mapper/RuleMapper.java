package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RuleDB;

/**
* @author odin
* @description 针对表【bld_rule】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.Rule
*/
@Mapper
public interface RuleMapper extends BaseMapper<RuleDB> {

    IPage<RuleDB> page(Page<RuleDB> ruleDBPage, RuleListByParamQuery query);
}




