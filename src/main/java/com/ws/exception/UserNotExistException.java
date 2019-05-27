package com.ws.exception;

/**
 * 自定义异常
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在");
    }
}
