package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator;

import java.util.List;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter.CheckFilter;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 14:37
 * Description:
 */
public interface IValidator<T> {

    default void validate(T t) {
        listCheckFilter().forEach(tCheckFilter -> tCheckFilter.check(t));
    }

    List<CheckFilter<T>> listCheckFilter();
}
