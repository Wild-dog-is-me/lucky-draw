package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.AcceptPrizeDB;

/**
* @author odin
* @description 针对表【bld_accept_prize】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.AcceptPrize
*/

@Mapper
public interface AcceptPrizeMapper extends BaseMapper<AcceptPrizeDB> {

    AcceptPrizeDB getByRecordId(@Param("recordId") Long recordId);

}




