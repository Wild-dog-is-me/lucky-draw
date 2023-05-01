package org.dog.lduser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.lduser.po.User;
import org.dog.lduser.service.UserService;
import org.dog.lduser.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【bld_user】的数据库操作Service实现
* @createDate 2023-05-01 22:47:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




