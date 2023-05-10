package com.kuaishou.raymond.algorithm.leetcode.hot100.h1hash;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&id=top-100-liked">49. 字母异位词分组</a>
 * - 哈希
 * 相似题目：{@link com.kuaishou.raymond.algorithm.leetcode.hot100.h3slidewindow.Hot438FindAnagrams}
 */
public class Hot49GroupAnagrams {

    /**
     * 0. 设置 HashMap，Key 为排序后的异或词，Value 为包含相同异或词的原字符串。
     * 1. 遍历所有字符串，将字符串转换成 char 数组后排序；
     * 2. 以排序后的 char 作为 HashMap 的 key，这样就可以让所有异或词的 key 相同；
     * 3. HashMap 的 values 即为所求。
     */
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
