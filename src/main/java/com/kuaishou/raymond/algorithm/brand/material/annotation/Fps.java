package com.kuaishou.raymond.algorithm.brand.material.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: raymond
 * @Datetime: 2023/11/16 10:20
 * Description: 帧率
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface Fps {

    /**
     * 最小帧率
     */
    int min();

    /**
     * 最大帧率
     */
    int max();

}
