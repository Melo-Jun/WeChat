
package com.melo.wechat.annotation;

import java.lang.annotation.*;

/**
 * @Description: 注解属性数据库对应列名
 * @author: Jun
 * @date: 11:08 2021/5/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    public String value() default "";
}
