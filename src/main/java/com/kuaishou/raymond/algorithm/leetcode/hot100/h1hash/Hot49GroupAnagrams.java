package com.kuaishou.raymond.algorithm.leetcode.hot100.h1hash;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 字母异或词分组
 */
public class Hot49GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> data = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            // 以排序后的字符为 key，将所有包含相同字符的字符串存储到同一个集合中。
            String key = String.valueOf(charArray);
            List<String> anagram = data.getOrDefault(key, new ArrayList<>());
            anagram.add(str);
            data.put(key, anagram);
        }
        return new ArrayList<>(data.values());
    }
}
