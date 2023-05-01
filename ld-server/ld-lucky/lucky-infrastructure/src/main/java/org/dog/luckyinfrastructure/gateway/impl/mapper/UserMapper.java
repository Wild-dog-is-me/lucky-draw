package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.UserDB;

/**
* @author odin
* @description 针对表【bld_user】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.User
*/
public interface UserMapper extends BaseMapper<UserDB> {

    UserDB findByUserName(@Param("id") Long id,@Param("username") String username);
}




