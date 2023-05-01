package org.dog.config.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.config.vo
 * @createTime 2022/11/26 - 15:38
 * @description
 */
@Builder
@ToString
@Getter
public class SuccessInfo extends ResultInfo{

    protected static final Integer DEFAULT_CODE = 20000;
    protected static final String DEFAULT_MESSAGE = "操作成功";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Object data;

    protected SuccessInfo(Object data) {
        super(true, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.data = data;
    }
}
