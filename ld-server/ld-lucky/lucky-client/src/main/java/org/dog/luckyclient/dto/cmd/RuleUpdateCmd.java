package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:05
 * @Description:
 */

@Data
public class RuleUpdateCmd extends Command {
    /**
     *
     */
    @NotNull(message = "id不为空")
    private Long id;

    /**
     * 规则名称
     */
    @NotNull(message = "名称不为空")
    private String ruleName;

    /**
     * 最大可参与次数
     */
    @NotNull(message = "参与次数不为空")
    private Integer maxJoinNumber;

    /**
     * 最大可中奖次数
     */
    @NotNull(message = "中奖次数不为空")
    private Integer maxWinningNumber;
}
