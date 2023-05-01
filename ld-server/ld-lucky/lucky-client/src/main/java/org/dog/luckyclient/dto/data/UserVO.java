package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/4/30 21:02
 * @Description: 展示给用户
 */

@Data
public class UserVO {

    /**
     *
     */
    private Long id;

    /**
     * 账号
     */
    private String username;


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
