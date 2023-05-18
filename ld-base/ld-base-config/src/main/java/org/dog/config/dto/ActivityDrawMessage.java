package org.dog.config.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Odin
 * @Date: 2023/5/17 19:53
 * @Description:
 */

@Data
@Accessors(chain = true)
public class ActivityDrawMessage {

    /**
     * 业务唯一id
     */
    private Long id;

    private String uuid;

    /**
     * JSON内容对象
     */
    private String body;
}
