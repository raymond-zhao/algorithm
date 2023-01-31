package com.kuaishou.raymond.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-01-31 17:37
 */
public class P3LengthOfLongestSubString {

    public static void main(String[] args) {
        String s = "abcdabcde";
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        P3LengthOfLongestSubString p3 = new P3LengthOfLongestSubString();
        System.out.println("p3.lengthOfLongestSubString(s) = " + p3.lengthOfLongestSubString(s));
        System.out.println("p3.lengthOfLongestSubString(s) = " + p3.lengthOfLongestSubString(s1));
        System.out.println("p3.lengthOfLongestSubString(s) = " + p3.lengthOfLongestSubString(s2));
        System.out.println("p3.lengthOfLongestSubString(s) = " + p3.lengthOfLongestSubString(s3));
    }

    /**
     * @param s 字符串
     * @return 最长不重复子串
     */
    private int lengthOfLongestSubString(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (int start = 0, end = 0; end < s.length(); end++) {
            char currentCharacter = s.charAt(end);
            if (map.containsKey(currentCharacter)) {
                // 如果当前字符之前已经出现过，则执行"喜新厌旧"策略。
                // 即：把新扫描到的字符加入滑动窗口，把老字符从滑动窗口中去除掉
                // （把老字符的索引下标 + 1，表示从下个字符开始，滑动窗口里的字符才不重复），
                // 相当于滑动窗口的窗口大小不变，但是整体向右平移了 1 个单位。
                start = Math.max(start, map.get(currentCharacter) + 1);
            }
            map.put(currentCharacter, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

}
