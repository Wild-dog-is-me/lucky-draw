package org.dog.luckydomain.activity;

import lombok.Getter;

/**
 * @Author: Odin
 * @Date: 2023/5/7 20:36
 * @Description:
 */
@Getter
public enum ActivityStatusEnum {

    NOT_START(0, "未开始"),

    START(1, "进行中"),

    END(2, "已结束"),

    ;

    private Integer value;

    private String description;

    ActivityStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
