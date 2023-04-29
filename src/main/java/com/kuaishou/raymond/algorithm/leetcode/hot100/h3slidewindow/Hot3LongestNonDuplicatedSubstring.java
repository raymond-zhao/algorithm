package com.kuaishou.raymond.algorithm.leetcode.hot100.h3slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-28 11:34
 */
public class Hot3LongestNonDuplicatedSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("lengthOfLongestSubstring(s) = " + lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;

        Map<Character/*字符*/, Integer/*字符上一次出现的索引*/> map = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                // 如果窗口内已经出现过该字符，将非重复字符的窗口的左边界更新为 end 与 上一次出现的位置的下一个位置中的较大值。
                start = Math.max(end, map.get(ch) + 1);
            }
            maxLength = Math.max(maxLength, end - start + 1);
            map.put(ch, end);
        }
        return maxLength;
    }
}
