package com.kuaishou.raymond.algorithm.leetcode.hot100.h3slidewindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-28 11:34
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">3. 无重复字符的最长子串</a>
 * - 滑动窗口
 * - 哈希
 */
public class Hot3LongestNonDuplicatedSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s2 = "tmmzuxt";
        String s3 = "dvdf";
        String s4 = "abba";
        System.out.println("lengthOfLongestSubstring(s) = " + lengthOfLongestSubstring(s4));
    }

    /**
     * 每次循环移动一次右边界，如果当前右边界新纳入窗口的字符之前已经出现过，
     * 则将这个已经出现过的字符更新为
     */
    public static int lengthOfLongestSubstring(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        // left：不重复子串的开始索引，[left, right] 之间无重复字符。
        for (int left = 0, right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                // 如当前字符之前已经出现过，则更新非重复字符的起始位置。
                // 必须取 max，要不然左指针可能会回退，参考 abba，left 会从 2 回退成 1，扩大区间。
                left = Math.max(left, map.get(ch) + 1);
            }
            maxLength = Math.max(maxLength, right - left + 1);
            map.put(ch, right);
        }
        return maxLength;
    }
}
