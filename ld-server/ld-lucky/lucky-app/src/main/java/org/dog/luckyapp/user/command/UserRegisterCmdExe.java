package org.dog.luckyapp.user.command;

import com.alibaba.cola.exception.SysException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.UserAssembler;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckydomain.gateway.UserGateway;
import org.dog.luckydomain.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/1 23:28
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserRegisterCmdExe {

    private final UserGateway userGateway;

    public UserVO execute(UserRegisterCmd cmd) {
        UserEntity oldEntity = userGateway.findByUserName(null, cmd.getUsername());
        if (Objects.nonNull(oldEntity)) {
            throw new SysException("账号已存在");
        }
        UserEntity entity = userGateway.save(UserAssembler.toAddEntity(cmd));
        return UserAssembler.toUserVO(entity);
    }

}
