package org.dog.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:09
 * @Description:
 */
@Data
public class AwardListByParamQuery extends PageQuery {

    private Long id;

    private Long activityId;

    private String activityName;

    private String awardName;
}

