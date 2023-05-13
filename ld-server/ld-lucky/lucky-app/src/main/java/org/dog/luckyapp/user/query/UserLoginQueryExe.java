package org.dog.luckyapp.user.query;

import com.alibaba.cola.exception.SysException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.UserAssembler;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserLoginQuery;
import org.dog.luckydomain.gateway.UserGateway;
import org.dog.luckydomain.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/2 12:21
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserLoginQueryExe {

    private final UserGateway userGateway;

    public UserVO execute(UserLoginQuery query) {
        final var userEntity = userGateway.findByUserName(null, query.getUsername());
        if (Objects.isNull(userEntity)) {
            throw new SysException("登陆失败，用户不存在");
        }
        userEntity.getPassword().equals(query.getPassword());
        if (Boolean.FALSE.equals(userEntity.getPassword().isEqual(query.getPassword()))){
            throw new SysException("登陆失败，密码错误");
        }
        return UserAssembler.toUserVO(userEntity);
    }
}
