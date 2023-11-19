package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.factory;

import java.util.List;
import java.util.stream.Collectors;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.OperationType;

/**
 * @Author: raymond
 * @Datetime: 2023/9/21 21:24
 * Description:
 */
public interface IFactory<P, T> {

    default List<T> build(List<P> items, OperationType operationType) {
        return items.stream().map(item -> build(item, operationType)).collect(Collectors.toList());
    }

    T build(P item, OperationType operationType);

    default List<P> render(List<T> items, OperationType operationType) {
        return items.stream().map(item -> render(item, operationType)).collect(Collectors.toList());
    }

    P render(T item, OperationType operationType);

}
