package org.dog.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:07
 * @Description:
 */
@Data
public class RuleListByParamQuery extends PageQuery {

    private Long id;

    private List<Long> ids;

    private String ruleName;

}
