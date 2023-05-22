package org.dog.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/5/21 00:14
 * @Description:
 */

@Data
public class RecordUpdateStatusCmd extends Command {

    /**
     *
     */
    private Long id;

    /**
     * 状态（0，1，2，3）
     */
    private Integer state;
}
