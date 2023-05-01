package org.dog.luckyadapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IUserService;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final IUserService iUserService;

    @PostMapping("/register")
    public UserVO register(@Validated @RequestBody UserRegisterCmd cmd) {
        return iUserService.register(cmd);
    }


}
