package org.dog.config.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.config.vo
 * @createTime 2022/11/26 - 15:35
 * @description
 */
@Getter
public class ResultInfo implements Serializable {

    protected Boolean result;
    protected Integer code;
    /**
     * message = null , 不序列化出去
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String message;

    protected ResultInfo(Boolean result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

}
