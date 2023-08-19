package com.kuaishou.raymond.algorithm.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: raymond
 * @Datetime: 2023/8/17 15:05
 * Description: 打点注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PerfLog {

    /**
     * 业务线
     */
    String bizType() default "";

    /**
     * 前缀
     */
    String prefix() default "";
}
