package org.dog.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.config.util.SecurityUtil;
import org.dog.luckyclient.api.IUserService;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckyclient.dto.query.UserLoginQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Odin
 * @Date: 2023/5/1 23:26
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/user")
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public UserVO register(@Validated @RequestBody UserRegisterCmd cmd) {
        return userService.register(cmd);
    }

    @PostMapping("/login")
    public String login(@Validated @RequestBody UserLoginQuery query) {
        return userService.login(query);
    }

    @GetMapping("/me")
    public UserVO me() {
        return userService.one(SecurityUtil.getUserId());
    }

    @PostMapping("/update")
    public UserVO update(@Validated @RequestBody UserUpdateCmd cmd) {
        return userService.update(cmd);
    }
}
