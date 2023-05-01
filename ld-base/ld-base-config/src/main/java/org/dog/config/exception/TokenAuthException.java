package org.dog.config.exception;


public class TokenAuthException extends RuntimeException {
    public TokenAuthException() {
    }
    public TokenAuthException(String message, Object... args) {
        super(String.format(message, args));
    }

    public TokenAuthException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public TokenAuthException(Throwable cause) {
        super(cause);
    }

}
