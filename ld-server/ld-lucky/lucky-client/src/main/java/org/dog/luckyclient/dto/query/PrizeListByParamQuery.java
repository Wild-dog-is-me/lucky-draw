package org.dog.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/5 13:01
 * @Description:
 */

@Data
public class PrizeListByParamQuery extends PageQuery {

    private Long id;

    private String prizeName;

    private Integer type;
}
