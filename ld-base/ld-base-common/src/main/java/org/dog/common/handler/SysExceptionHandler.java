package org.dog.common.handler;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.config.exception.TokenAuthException;
import org.dog.config.vo.FailInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/4/29 14:59
 * @Description:
 */

@Slf4j
@RestControllerAdvice
public class SysExceptionHandler {

    /**
     * 最大错误兜底
     */
    @ExceptionHandler(value = Exception.class)
    public FailInfo exception(Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return FailInfo.builder().exception(ex.getMessage()).build();
    }

    @ExceptionHandler(value = BindException.class)
    public FailInfo exception(BindException ex) {
        String defaultMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError().getDefaultMessage());
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return FailInfo.builder().exception(defaultMessage).build();
    }

    @ExceptionHandler(value = LdException.class)
    public FailInfo sysException(Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        var failInfo = FailInfo.builder().exception(ex.getMessage()).build();
        return failInfo;
    }

    @ExceptionHandler(value = TokenAuthException.class)
    public FailInfo notAuthException(Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(401, ex.getMessage());
    }

    @ExceptionHandler(value = MysqlDataTruncation.class)
    public FailInfo mysqlDataTruncation(Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(500, ex.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public FailInfo dataIntegrityViolationException(Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        String message = ex.getMessage();
        String[] split = message.split("\r\n###");
        for (String str : split) {
            if (str.trim().isBlank() || str.trim().contains("Error")){
                continue;
            }
            String[] split1 = str.split(":");
            if (split1.length > 0) {
                message = split1[split1.length - 1].trim();
            }
        }
        return new FailInfo(500, message);
    }

}
