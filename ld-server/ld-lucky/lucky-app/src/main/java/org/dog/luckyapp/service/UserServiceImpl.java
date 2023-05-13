package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.config.util.JwtUtil;
import org.dog.luckyapp.user.command.UserRegisterCmdExe;
import org.dog.luckyapp.user.command.UserUpdateCmdExe;
import org.dog.luckyapp.user.query.UserListByParamQueryExe;
import org.dog.luckyapp.user.query.UserLoginQueryExe;
import org.dog.luckyclient.api.IUserService;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckyclient.dto.query.UserLoginQuery;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    private final UserLoginQueryExe userLoginQueryExe;
    private final UserListByParamQueryExe userListByParamQueryExe;
    private final UserUpdateCmdExe userUpdateCmdExe;

    @Override
    public UserVO register(UserRegisterCmd cmd) {
        return userRegisterCmdExe.execute(cmd);
    }

    @Override
    public String login(UserLoginQuery query) {
        UserVO userVO = userLoginQueryExe.execute(query);
        // JWT

        return JwtUtil.createToken(Map.of(
                "username", userVO.getUsername(),
                "name", userVO.getName(),
                "phone", userVO.getPhone(),
                "id", userVO.getId()
        ));
    }

    @Override
    public IPage<UserVO> page(UserListByParamQuery query) {
        return userListByParamQueryExe.execute(query);
    }

    @Override
    public UserVO one(Long id) {
        final var query = new UserListByParamQuery();
        query.setId(id);
        IPage<UserVO> iPage = userListByParamQueryExe.execute(query);

        if (CollUtil.isEmpty(iPage.getRecords())) {
            throw new LdException("该用户不存在！");
        }
        return iPage.getRecords().get(0);
    }

    @Override
    public UserVO update(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }

}
