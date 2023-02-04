package com.kuaishou.raymond.algorithm.swordtooffer.day5_search_medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个只出现一次的数字
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 */
public class P50 {

    public static void main(String[] args) {
        String s = "abaccdeff";
        String s1 = "";
        String s2 = "leetcode";
        P50 p50 = new P50();
        System.out.println("p50.firstUniqChar(s) = " + p50.firstUniqChar(s));
        System.out.println("p50.firstUniqChar(s) = " + p50.firstUniqChar(s1));
        System.out.println("p50.firstUniqChar(s) = " + p50.firstUniqChar(s2));
    }

    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public char firstUniqCharOfOrderedString(String s) {
        int[] helper = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            helper[c - 'a']++;
        }

        for (int i = 0; i < helper.length; i++) {
            if (helper[i] == 1) {
                return (char) (i + 'a');
            }
        }
        return ' ';
    }

}
