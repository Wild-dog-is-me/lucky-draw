package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckyclient.dto.query.UserLoginQuery;
import org.springframework.context.annotation.Primary;

/**
 * @Author: Odin
 * @Date: 2023/5/1 23:08
 * @Description:
 */

@Primary
public interface IUserService {

    /**
     * 注册用户
     *
     * @param cmd
     * @return
     */
    UserVO register(UserRegisterCmd cmd);

    /**
     * 用户登陆
     *
     * @param query
     * @return
     */
    String login(UserLoginQuery query);

    /**
     * 分页查询用户信息
     *
     * @param query
     * @return
     */
    IPage<UserVO> page(UserListByParamQuery query);

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    UserVO one(Long id);

    /**
     * 用户修改
     * @param cmd
     * @return
     */
    UserVO update(UserUpdateCmd cmd);

}
