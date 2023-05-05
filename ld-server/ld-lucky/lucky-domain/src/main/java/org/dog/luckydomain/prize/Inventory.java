package org.dog.luckydomain.prize;

import lombok.Getter;
import org.dog.config.exception.LdException;

/**
 * @Author: Odin
 * @Date: 2023/5/5 12:56
 * @Description:
 */

@Getter
public class Inventory {

    private Integer inventory;

    public Inventory(Integer inventory) {
        if (inventory < 0) {
            throw new LdException("库存数量请大于等于0");
        }
        this.inventory = inventory;
    }
}
