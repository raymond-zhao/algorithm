package com.kuaishou.raymond.algorithm.brand.material.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: raymond
 * @Datetime: 2023/11/16 10:20
 * Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface Format {

    /**
     * 支持的素材格式
     */
    String[] formats() default {};
}
