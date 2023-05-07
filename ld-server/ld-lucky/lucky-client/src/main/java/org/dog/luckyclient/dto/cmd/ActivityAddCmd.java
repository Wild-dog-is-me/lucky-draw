package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/7 21:57
 * @Description:
 */
@Data
public class ActivityAddCmd extends Command {

    /**
     * 活动名称
     */
    @NotNull(message = "活动名称不为空")
    private String activityName;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 描述
     */
    @NotNull(message = "描述不为空")
    private String describe;
}
