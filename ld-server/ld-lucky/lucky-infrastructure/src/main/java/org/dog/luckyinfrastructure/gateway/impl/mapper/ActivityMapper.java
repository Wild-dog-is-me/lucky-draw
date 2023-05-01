package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.ActivityDB;

/**
* @author odin
* @description 针对表【bld_activity】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.Activity
*/
@Mapper
public interface ActivityMapper extends BaseMapper<ActivityDB> {

}




