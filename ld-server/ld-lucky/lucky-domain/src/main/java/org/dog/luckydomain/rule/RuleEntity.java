package org.dog.luckydomain.rule;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/6 09:58
 * @Description:
 */

@Entity
@Data
public class RuleEntity {

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
    private MinNumber maxJoinNumber;

    /**
     * 最大可中奖次数
     */
    private MinNumber maxWinningNumber;

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

    /**
     *
     */
    private String updater;
}
