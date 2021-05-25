package com.melo.wechat.annotation.log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: 日志记录注解
 * @author: Jun
 * @date: 9:00 2021/5/18
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {
    public String value() default "";
}
