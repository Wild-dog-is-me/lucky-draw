package org.dog.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dog.luckyclient.dto.cmd.RecordAddCmd;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;

/**
 * @Author: Odin
 * @Date: 2023/5/21 00:07
 * @Description:
 */
public interface IRecordService {

    IPage<RecordVO> page(RecordListByParamQuery query);

    RecordVO add(RecordAddCmd cmd);

    Boolean update(RecordUpdateStatusCmd cmd);

    /**
     *
     * @param recordId
     * @return 1：商品，2：金额
     */
    Integer prizeType(Long recordId);

    Boolean exchangeMoney(Long recordId);

}
