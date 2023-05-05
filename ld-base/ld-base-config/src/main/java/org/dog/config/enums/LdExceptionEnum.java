package org.dog.config.enums;

import lombok.Getter;
import org.dog.config.vo.FailInfo;

/**
 * @Author: Odin
 * @Date: 2023/5/5 14:29
 * @Description:
 */

@Getter
public enum LdExceptionEnum {

    ADD_ERROR(FailInfo.DEFAULT_CODE, "保存数据失败！"),

    UPDATE_ERROR(FailInfo.DEFAULT_CODE, "保存数据失败！"),

    ;
    private Integer code;

    private String description;

    LdExceptionEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }
}
