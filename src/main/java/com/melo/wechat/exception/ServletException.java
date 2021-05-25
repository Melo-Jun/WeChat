package com.melo.wechat.exception;

/**
 * @Description: Servlet异常
 * @author: Jun
 * @date: 10:55 2021/5/25
 */
public class ServletException extends RuntimeException{

    public ServletException() {
    }

    public ServletException(String message) {
        super(message);
    }

    public ServletException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServletException(Throwable cause) {
        super(cause);
    }

    public ServletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
