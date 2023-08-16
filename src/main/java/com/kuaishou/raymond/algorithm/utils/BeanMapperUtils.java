package com.kuaishou.raymond.algorithm.utils;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import lombok.SneakyThrows;

/**
 * @Author: raymond
 * @Datetime: 2023/8/11 11:47
 * Description:
 */
public class BeanMapperUtils {

    private BeanMapperUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static <S, T> T map(S source, Class<T> klass) {
        return map(source, klass, (String) null);
    }

    @SneakyThrows
    public static <S, T> T map(S source, Class<T> klass, String... ignoredProperties) {
        if (Objects.isNull(source)) {
            return null;
        }
        T t = klass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(source, t, ignoredProperties);
        return t;
    }
}
