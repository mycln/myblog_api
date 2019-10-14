package com.clouderwork.annotation;

import java.lang.annotation.*;

/**
 * @Author heyanfeng
 * @Contact
 * @Description
 * @Date Created in 2018/11/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLogs {

    /**
     *  描述
     */
    String description() default "";
}
