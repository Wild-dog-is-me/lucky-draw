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
