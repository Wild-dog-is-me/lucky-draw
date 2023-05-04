package org.dog.luckyinfrastructure.gateway.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckydomain.user.UserEntity;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.UserDB;

/**
* @author odin
* @description 针对表【bld_user】的数据库操作Mapper
* @createDate 2023-04-30 20:11:05
* @Entity org.dog.lduser.po.User
*/
public interface UserMapper extends BaseMapper<UserDB> {

    UserDB findByUserName(@Param("id") Long id,@Param("username") String username);

    IPage<UserDB> listByParamQuery(@Param("userEntityPage") Page<UserEntity> userEntityPage,@Param("query") UserListByParamQuery query);

}




