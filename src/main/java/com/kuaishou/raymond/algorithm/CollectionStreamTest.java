package com.kuaishou.raymond.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-12-22 11:49
 */
@Slf4j
public class CollectionStreamTest {

    public static void main(String[] args) {
        Map<Integer, Set<Integer>> data = Maps.newHashMap();
        data.put(1, Sets.newHashSet(1, 2, 3));
        data.put(2, Sets.newHashSet(4, 5, 6));
        Set<Integer> res = data.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        log.info("res = {}", res);
    }

}
