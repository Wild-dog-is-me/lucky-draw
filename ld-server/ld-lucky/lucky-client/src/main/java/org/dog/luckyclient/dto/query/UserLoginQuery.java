package org.dog.luckyclient.dto.query;

import lombok.Data;

import javax.management.Query;
import javax.validation.constraints.NotNull;

/**
 * @Author: Odin
 * @Date: 2023/4/30 21:31
 * @Description:
 */

@Data
public class UserLoginQuery extends Query {

    @NotNull(message = "用户名不为空")
    private String username;

    @NotNull(message = "密码不为空")
    private String password;

}
