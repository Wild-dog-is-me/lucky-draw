package org.dog.luckydomain.user;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/4/30 20:48
 * @Description:
 */

@Entity
@Data
public class UserEntity {

      /*
    这是个领域对象，所以该对象包含了，用户该有的基本功能
    1、校验用户名
    2、加密密码
    3、判断密码是否相等
     */

    /**
     *
     */
    private Long id;

    /**
     * 账号
     */
    private UserName username;

    /**
     * 密码
     */
    private PassWord password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private LocalDateTime updateTime;

}
