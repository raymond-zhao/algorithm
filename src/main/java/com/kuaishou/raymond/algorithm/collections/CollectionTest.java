package com.kuaishou.raymond.algorithm.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

import lombok.Data;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-01-05 15:16
 */
public class CollectionTest {

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.setField(Collections.singleton(1L));

        Demo1 demo1 = new Demo1();

        System.out.println("demo1 = " + demo1);
    }

    @Data
    public static class Demo1 {
        private List<Long> field;
    }

    @Data
    public static class Demo2 {
        private Collection<Long> field;
    }
}
