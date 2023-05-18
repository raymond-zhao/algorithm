package com.kuaishou.raymond.algorithm.interview.didi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/5/17 23:23
 * 题目：
 */
public class SortCharacterByFrequency {

    public static void main(String[] args) {
        String s = "tree";
        System.out.println("frequencySort(s) = " + frequencySort(s));
        String s1 = "Aabb";
        System.out.println("frequencySort(s1) = " + frequencySort(s1));
    }

    public static String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        // 统计词频
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 按词频排序
        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));

        StringBuilder builder = new StringBuilder();
        for (char c : list) {
            builder.append(String.valueOf(c).repeat(map.get(c)));
        }
        return builder.toString();
    }

}
