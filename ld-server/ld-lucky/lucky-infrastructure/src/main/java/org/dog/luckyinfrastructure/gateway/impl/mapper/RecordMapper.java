package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.RecordDB;

import java.math.BigDecimal;

/**
* @author odin
* @description 针对表【bld_record】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.Record
*/
@Mapper
public interface RecordMapper extends BaseMapper<RecordDB> {

    IPage<RecordDB> page(@Param("recordDBPage") Page<RecordDB> recordDBPage, @Param("query") RecordListByParamQuery query);

    Integer updateStatus(@Param("id") Long id, @Param("status") Integer status);

    BigDecimal getPrizeMoneyByRecordId(@Param("recordId") Long recordId);

}




