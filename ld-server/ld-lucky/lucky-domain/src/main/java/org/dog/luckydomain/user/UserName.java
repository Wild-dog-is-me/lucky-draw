package org.dog.luckydomain.user;

import lombok.Getter;
import org.dog.config.exception.LdException;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/4/30 20:50
 * @Description:
 */

@Getter
public class UserName {

    private String username;

    public UserName(String username) {
        if (Objects.isNull(username)) {
            throw new LdException("账号不能为空");
        }
        this.username = username;
    }
}
