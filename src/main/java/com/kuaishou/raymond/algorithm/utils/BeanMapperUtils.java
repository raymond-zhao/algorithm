package com.kuaishou.raymond.algorithm.utils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/8/11 11:47
 * Description:
 */
@Slf4j
public class BeanMapperUtils {

    private static final Gson gson = new Gson();

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

    public static <E, C extends Collection<E>> C fromJson(String json, Class<C> collectionType, Class<E> elementType) {
        Type collectionTypeToken = TypeToken.getParameterized(collectionType, elementType).getType();
        return gson.fromJson(json, collectionTypeToken);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> elementType) {
        return fromJson(json, List.class, elementType);
    }

    public static <T> Set<T> fromJsonSet(String json, Class<T> elementType) {
        return fromJson(json, Set.class, elementType);
    }

    public static void main(String[] args) {
        String str = "[1,2,3,4,5]";
        List<Integer> list = fromJsonList(str, Integer.class);
        log.info("list = {}", list);
        Set<String> strings = fromJsonSet(str, String.class);
        log.info("strings = {}", strings);
    }
}
