package com.melo.wechat.annotation.dao;

import java.lang.annotation.*;

/**
 * @Description: 负责注解是否需要事务处理
 * @author: Jun
 * @date: 16:36 2021/5/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transaction {
    public String value() default "";
}
