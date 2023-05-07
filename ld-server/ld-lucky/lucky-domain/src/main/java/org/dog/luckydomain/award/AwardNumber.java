package org.dog.luckydomain.award;

import lombok.Getter;
import org.dog.config.exception.LdException;

/**
 * @Author: Odin
 * @Date: 2023/5/6 17:06
 * @Description:
 */
@Getter
public class AwardNumber {

    private Integer number;

    public AwardNumber(Integer number) {

        if (number < 0) {
            throw new LdException("奖项数量不合法，需大于等于 0");
        }

        this.number = number;
    }

}
