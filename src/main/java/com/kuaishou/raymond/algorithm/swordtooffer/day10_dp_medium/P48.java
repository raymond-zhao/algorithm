package com.kuaishou.raymond.algorithm.swordtooffer.day10_dp_medium;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/">最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * s.length <= 40000
 */
public class P48 {

    public static void main(String[] args) {
        P48 p48 = new P48();
        String s = "abcabcbb";
        System.out.println("p48.lengthOfLongestSubstring(s) = " + p48.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            map.put(ch, end);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

}
