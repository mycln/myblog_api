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
public @interface ControllerLogs {
    /**
     *  描述
     * @return
     */
    String description() default "";
}
