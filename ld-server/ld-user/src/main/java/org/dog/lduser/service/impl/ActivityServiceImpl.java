package org.dog.lduser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.lduser.po.Activity;
import org.dog.lduser.service.ActivityService;
import org.dog.lduser.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【bld_activity】的数据库操作Service实现
* @createDate 2023-05-01 22:47:08
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




