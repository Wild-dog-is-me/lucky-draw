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
public class FailInfo extends ResultInfo{

    public static final Integer DEFAULT_CODE = 50000;
    protected static final String DEFAULT_MESSAGE = "操作失败";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String exception;


    public FailInfo(String exception) {
        super(false, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.exception = exception;
    }

    public FailInfo(Integer code, String exception) {
        super(false, code, DEFAULT_MESSAGE);
        this.exception = exception;
    }
}
