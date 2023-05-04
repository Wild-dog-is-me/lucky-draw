package org.dog.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckydomain.user.UserEntity;

/**
 * @Author: Odin
 * @Date: 2023/5/1 23:33
 * @Description:
 */


public interface UserGateway {

    UserEntity save(UserEntity entity);

    UserEntity findByUserName(Long id, String username);

    IPage<UserEntity> listByParamQuery(UserListByParamQuery query);
}
