package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AwardDB;

/**
 * @author odin
 * @description 针对表【bld_award】的数据库操作Mapper
 * @createDate 2023-04-30 20:11:05
 * @Entity org.dog.lduser.po.Award
 */
@Mapper
public interface AwardMapper extends BaseMapper<AwardDB> {

    IPage<AwardDB> page(@Param("awardDBPage") Page<AwardDB> awardDBPage, @Param("query") AwardListByParamQuery query);

    int deductionAwardNumber(@Param("awardId") Long awardId, @Param("number") Integer number);

}




