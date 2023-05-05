package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyclient.dto.query.PrizeListByParamQuery;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.PrizeDB;

/**
 * @author odin
 * @description 针对表【bld_prize】的数据库操作Mapper
 * @createDate 2023-04-30 20:11:05
 * @Entity org.dog.lduser.po.Prize
 */
@Mapper
public interface PrizeMapper extends BaseMapper<PrizeDB> {

    IPage<PrizeDB> page(@Param("page") Page<PrizeDB> prizeDBPage, @Param("query") PrizeListByParamQuery query);
}




