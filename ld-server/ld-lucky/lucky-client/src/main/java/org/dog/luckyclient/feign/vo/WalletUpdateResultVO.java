package org.dog.luckyclient.feign.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Odin
 * @Date: 2023/5/26 16:02
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WalletUpdateResultVO {

    private Boolean result;

}
