package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-25 19:09
 * <a href="https://leetcode.cn/problems/group-anagrams/">...</a>
 * 字母异或词分组
 */
public class P49GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("groupAnagrams(strs) = " + groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            List<String> anagram = map.getOrDefault(key, new ArrayList<>());
            anagram.add(str);
            map.put(key, anagram);
        }
        return new ArrayList<>(map.values());
    }
}
