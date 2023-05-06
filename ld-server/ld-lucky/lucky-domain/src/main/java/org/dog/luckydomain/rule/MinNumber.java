package org.dog.luckydomain.rule;

import lombok.Getter;
import org.dog.config.exception.LdException;

/**
 * @Author: Odin
 * @Date: 2023/5/6 10:00
 * @Description:
 */

@Getter
public class MinNumber {

    private Integer number;

    public MinNumber(Integer number) {
        if (number < 1) {
            throw new LdException("规则次数必须大于等于1");
        }
        this.number = number;
    }
}
