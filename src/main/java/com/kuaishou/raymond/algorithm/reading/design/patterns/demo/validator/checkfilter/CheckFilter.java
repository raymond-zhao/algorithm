package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 14:16
 * Description:
 */
public interface CheckFilter<T> {

    void check(T t);

}
