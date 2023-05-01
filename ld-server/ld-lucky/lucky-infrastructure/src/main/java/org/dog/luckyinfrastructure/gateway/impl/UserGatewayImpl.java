package org.dog.luckyinfrastructure.gateway.impl;

import com.alibaba.cola.exception.SysException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckydomain.gateway.UserGateway;
import org.dog.luckydomain.user.UserEntity;
import org.dog.luckyinfrastructure.convertor.UserConvertor;
import org.dog.luckyinfrastructure.gateway.impl.dataobject.UserDB;
import org.dog.luckyinfrastructure.gateway.impl.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/2 00:06
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserEntity entity) {
        UserDB userDB = UserConvertor.toUserDB(entity);
        int res = userMapper.insert(userDB);
        if (res <= 0) {
            throw new SysException("注册失败");
        }
        return UserConvertor.toEntity(userDB);
    }

    @Override
    public UserEntity findByUserName(Long id, String username) {
        UserDB userDB = userMapper.findByUserName(id,username);
        if (Objects.isNull(userDB)) {
            return null;
        }
        return UserConvertor.toEntity(userDB);
    }
}
