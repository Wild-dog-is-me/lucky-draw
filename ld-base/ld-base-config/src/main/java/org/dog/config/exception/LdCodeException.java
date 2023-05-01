package org.dog.config.exception;


import org.dog.config.vo.FailInfo;

public class LdCodeException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public LdCodeException() {
    }

    public LdCodeException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public LdCodeException(String message, Object... args) {
        super(String.format(message, args));
        this.code = FailInfo.DEFAULT_CODE;
    }

    public LdCodeException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
        this.code = FailInfo.DEFAULT_CODE;
    }

    public LdCodeException(Throwable cause) {
        super(cause);
        this.code = FailInfo.DEFAULT_CODE;
    }

}
