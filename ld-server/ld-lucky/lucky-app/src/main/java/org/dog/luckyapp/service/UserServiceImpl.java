package org.dog.luckyapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.service.user.command.UserRegisterCmdExe;
import org.dog.luckyclient.api.IUserService;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckyclient.dto.query.UserLoginQuery;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/5/1 23:12
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRegisterCmdExe userRegisterCmdExe;

    @Override
    public UserVO register(UserRegisterCmd cmd) {
        return userRegisterCmdExe.execute(cmd);
    }

    @Override
    public String login(UserLoginQuery query) {
        return null;
    }

    @Override
    public IPage<UserVO> page(UserListByParamQuery query) {
        return null;
    }

    @Override
    public UserVO one(Long id) {
        return null;
    }

    @Override
    public UserVO update(UserUpdateCmd cmd) {
        return null;
    }
}
