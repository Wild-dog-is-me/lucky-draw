package org.dog.luckyapp.service.user.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.UserAssembler;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckydomain.gateway.UserGateway;
import org.dog.luckydomain.user.UserEntity;
import org.dog.luckyinfrastructure.convertor.UserConvertor;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/4 15:52
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserUpdateCmdExe {

    private final UserGateway userGateway;


    public UserVO execute(UserUpdateCmd cmd) {
        UserEntity entity = userGateway.save(UserAssembler.toUpdateEntity(cmd));
        return UserAssembler.toUserVO(entity);
    }

}
