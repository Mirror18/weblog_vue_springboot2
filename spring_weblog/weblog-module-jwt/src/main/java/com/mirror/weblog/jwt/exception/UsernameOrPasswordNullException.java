package com.mirror.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mirror
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
