package com.kuaishou.raymond.algorithm.leetcode.hot100.h3slidewindow;

import java.util.*;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-28 15:01
 * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/">438. 找到字符串中所有字母异位词</a>
 * - 滑动窗口
 */
public class Hot438FindAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println("findAnagrams(s, p) = " + findAnagrams(s, p)); // [0, 6]
        // System.out.println("findAnagrams(\"abab\", \"ab\") = " + findAnagrams("abab", "ab")); // [0,1,2]
    }

    /**
     * 滑动窗口
     */
    public static List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) {
            return new ArrayList<>();
        }
        // 手动哈希表
        // Key：将每个字符映射为以其 ASCII 值作为索引
        // Value：字符出现的次数
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        // 遍历 p 串，初始化 p 串的哈希结果，以及 s 中第一个窗口的结果。
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        List<Integer> data = new ArrayList<>();
        // 判断第一个窗口是否满足条件
        // 相等条件：出现的字符相同，并且每个字符出现的数量也相同。
        if (Arrays.equals(pCount, sCount)) {
            data.add(0);
        }
        // 将窗口向后滑动
        for (int idx = 0; idx < s.length() - p.length(); ++idx) {
            // 滑动窗口右移，将左边界中的字符数量减一。
            sCount[s.charAt(idx) - 'a']--;
            // 滑动窗口右移，将新诚如窗口右边界的字符数量加一。
            sCount[s.charAt(idx + p.length()) - 'a']++;
            // 判断新形成的窗口是否满足条件
            if (Arrays.equals(sCount, pCount)) {
                // 因为我们计算的是下一个窗口，所以起始索引需要加 1.
                data.add(idx + 1);
            }
        }
        return data;
    }

    /**
     * m=s.length, n=p.length
     * 时间复杂度：O((m-n)*m)
     * 空间复杂度：O(m)
     * 此方法很可能正确，但是超时。
     */
    public static List<Integer> findAnagramsII(String s, String p) {
        List<Integer> data = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0 || p.length() > s.length()) {
            return data;
        }
        // 以字符 ASCII 为索引创建一个数组作为窗口
        char[] chars = p.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        // O(n): 将所有字母存储Set中
        for (char c : chars) {
            // 记录每个字符及其出现的次数
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i <= s.length() - p.length(); i++) {
            // 截取从 i 开始，长度为 len(p) 的子串
            String substring = s.substring(i, i + p.length());
            // 检查该子串是否包含所有 p 中的字符
            if (containsAllChars(map, substring.toCharArray())) {
                data.add(i);
            }
        }

        return data;
    }

    private static boolean containsAllChars(Map<Character, Integer> map, char[] chars) {
        Map<Character, Integer> pMap = new HashMap<>();
        for (char ch : chars) {
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }

        if (pMap.keySet().size() != map.keySet().size()) {
            // 如果 s 与 p 含有的字符数量不同
            return false;
        }

        for (char c : chars) {
            if (!Objects.equals(map.get(c), pMap.get(c))) {
                return false;
            }
        }
        return true;
    }
}
