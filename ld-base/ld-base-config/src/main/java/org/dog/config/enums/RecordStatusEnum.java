package org.dog.config.enums;

import lombok.Getter;

/**
 * @Author: Odin
 * @Date: 2023/5/5 14:30
 * @Description:
 */
@Getter
public enum RecordStatusEnum {

    STATUE_0(0, "用户不可见"),

    STATUE_1(1, "待领取奖品"),

    STATUE_2(2, "待运营人员确认"),

    STATUE_3(3, "待用户签收"),

    STATUE_4(4, "流程结束"),

    ;
    private Integer value;

    private String description;


    RecordStatusEnum(Integer value, String description){
        this.value = value;
        this.description = description;
    }

}
