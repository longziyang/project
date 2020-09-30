package com.project.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 *
 */
public class DefaultAuthException extends AuthenticationException {

    public DefaultAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public DefaultAuthException(String msg) {
        super(msg);
    }
}
