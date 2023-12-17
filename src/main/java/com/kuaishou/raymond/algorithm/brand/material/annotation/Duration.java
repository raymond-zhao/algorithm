package com.kuaishou.raymond.algorithm.brand.material.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: raymond
 * @Datetime: 2023/11/16 10:20
 * Description: 视频时长（毫秒）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface Duration {

    /**
     * 最小时长
     */
    int min();

    /**
     * 最大时长
     */
    int max();

}
