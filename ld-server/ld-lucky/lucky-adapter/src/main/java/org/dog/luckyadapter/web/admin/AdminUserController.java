package org.dog.luckyadapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyclient.api.IUserService;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/5/5 00:10
 * @Description:
 */
@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/admin/v1/user")
public class AdminUserController {

    private final IUserService userService;

    @PostMapping("/page")
    public IPage<UserVO> page(@RequestBody UserListByParamQuery query) {
        return userService.page(query);
    }


    @PostMapping("/one")
    public UserVO one(@RequestParam(value = "id") Long id) {
        return userService.one(id);
    }
}
