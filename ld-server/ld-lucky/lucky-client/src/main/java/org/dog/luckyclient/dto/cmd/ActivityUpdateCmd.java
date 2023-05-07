package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/7 21:59
 * @Description:
 */
@Data
public class ActivityUpdateCmd extends Command {

    @NotNull(message = "id不为空")
    private Long id;

    /**
     * 活动名称
     */
    @NotNull(message = "活动名称不为空")
    private String activityName;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不为空")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不为空")
    private LocalDateTime endTime;

    /**
     * 描述
     */
    @NotNull(message = "描述不为空")
    private String describe;
}
