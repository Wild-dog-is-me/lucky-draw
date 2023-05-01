package org.dog.lduser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.lduser.po.Record;
import org.dog.lduser.service.RecordService;
import org.dog.lduser.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【bld_record】的数据库操作Service实现
* @createDate 2023-05-01 22:47:08
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

}




