package com.kuaishou.raymond.algorithm.leetcode.hot100.h4substring;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 76. 最小覆盖子串
 * 题解：<a href="https://leetcode.cn/problems/minimum-window-substring/solution/leetcode-76-zui-xiao-fu-gai-zi-chuan-cja-lmqz/">...</a>
 */
public class Hot76MinWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("minWindow(s, t) = " + minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        // 存储 s, t 中每个字符与其出现的次数
        Map<Character, Integer> sCharToCount = new HashMap<>();
        Map<Character, Integer> tCharToCount = new HashMap<>();
        // 初始化 t map
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            tCharToCount.put(ch, tCharToCount.getOrDefault(ch, 0) + 1);
        }
        // 与结果有关的变量
        String substring = "";
        // 最小覆盖子串的长度
        int minLength = Integer.MAX_VALUE;
        // s 中与 t 串已成功匹配的数量
        int counter = 0;

        // 开始滑动窗口
        for (int left = 0, right = 0; right < s.length(); right++) {
            // 滑动窗口右边界：s 串中的当前字符
            char ch = s.charAt(right);
            // 维护 s map
            sCharToCount.put(ch, sCharToCount.getOrDefault(ch, 0) + 1);

            // 如果子串 t 中包含当前字符，但是 s 中出现的这个字符的数量还没有达到子串 t 中所需的数量。
            if (tCharToCount.containsKey(ch) && sCharToCount.get(ch) <= tCharToCount.get(ch)) {
                counter++;
            }
            // 滑动窗口左边界：如果子串 t 中不包含该字符，或者 s 串中出现的字符数量已经超过子串 t 所需的数量。
            while (left < right && (!tCharToCount.containsKey(s.charAt(left)) || sCharToCount.get(s.charAt(left)) > tCharToCount.get(s.charAt(left)))) {
                int count = sCharToCount.get(s.charAt(left)) - 1;
                sCharToCount.put(s.charAt(left), count);
                left++;
            }
            // 更新最小子串长度
            if (counter == t.length() && right - left + 1 < minLength) {
                minLength = right - left + 1;
                substring = s.substring(left, right + 1);
            }
        }
        return substring;
    }

}
