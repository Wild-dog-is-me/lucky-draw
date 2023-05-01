package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Odin
 * @Date: 2023/4/30 21:41
 * @Description:
 */

@Data
public class UserUpdateCmd extends Command {

    @NotNull(message = "id不为空")
    private Long id;

    /**
     * 账号
     */
    @NotNull(message = "账号不为空")
    private String username;

    private String password;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不为空")
    private String name;

    /**
     * 电话
     */
    @NotNull(message = "电话不为空")
    private String phone;

}
