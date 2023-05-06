package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:06
 * @Description:
 */
@Data
public class RuleVO {
    /**
     *
     */
    private Long id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 最大可参与次数
     */
    private Integer maxJoinNumber;

    /**
     * 最大可中奖次数
     */
    private Integer maxWinningNumber;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    private LocalDateTime updateTime;

    private String updater;
}

