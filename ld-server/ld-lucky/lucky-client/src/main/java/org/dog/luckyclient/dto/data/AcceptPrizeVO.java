package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:55
 * @Description:
 */
@Data
public class AcceptPrizeVO {

    private Long id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     *
     */
    private LocalDateTime createTime;
}
