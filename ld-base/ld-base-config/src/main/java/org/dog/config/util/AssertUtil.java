package org.dog.config.util;

import org.dog.config.exception.LdCodeException;
import org.dog.config.exception.LdException;

/**
 * @Author: Odin
 * @Date: 2023/5/4 14:44
 * @Description:
 */
public class AssertUtil {

    public static void isTrue(Boolean expression, String message) {
        if (expression) {
            throw new LdException(message);
        }
    }

    public static void isFalse(Boolean expression, String message) {
        if (!expression) {
            throw new LdException(message);
        }
    }
}
