package org.dog.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/21 00:11
 * @Description:
 */

@Data
public class RecordListByParamQuery extends PageQuery {

    private Long recordId;

    private Long userId;

    private Long activityId;

    /**
     * true：中奖，false：未中奖
     */
    private Boolean winTheLottery;


    private Integer status;

}
