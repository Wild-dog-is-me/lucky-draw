package org.dog.luckyinfrastructure.gateway.impl;

import com.alibaba.cola.exception.SysException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
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
        if (Objects.isNull(entity.getId())) {
            return addUser(entity);
        }
        return updateUser(entity);
    }

    private UserEntity updateUser(UserEntity entity) {
        UserDB userDB = UserConvertor.toUserDB(entity);
        int res = userMapper.updateById(userDB);
        if (res <= 0) {
            throw new SysException("修改失败");
        }
        return UserConvertor.toEntity(userDB);
    }

    private UserEntity addUser(UserEntity entity) {
        UserDB userDB = new UserDB();

        userDB.setId(entity.getId());
        userDB.setUsername(entity.getUsername().getUsername());
        if (Objects.nonNull(entity.getPassword())){
            userDB.setPassword(entity.getPassword().getEncryptionPassWord().getPassword());
        }
        userDB.setName(entity.getName());
        userDB.setPhone(entity.getPhone());
        userDB.setCreateTime(entity.getCreateTime());
        userDB.setCreator(entity.getUsername().getUsername());
        userDB.setUpdateTime(entity.getUpdateTime());
        userDB.setUpdater(entity.getUsername().getUsername());

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

    @Override
    public IPage<UserEntity> listByParamQuery(UserListByParamQuery query) {
        IPage<UserDB> userDBIPage = userMapper.listByParamQuery(new Page<UserEntity>(query.getPageIndex(), query.getPageSize()), query);

        return userDBIPage.convert(UserConvertor::toEntity);
    }
}
