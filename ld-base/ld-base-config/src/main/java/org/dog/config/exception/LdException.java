package org.dog.config.exception;


public class LdException extends RuntimeException {
    public LdException() {
    }
    public LdException(String message, Object... args) {
        super(String.format(message, args));
    }

    public LdException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public LdException(Throwable cause) {
        super(cause);
    }

}
