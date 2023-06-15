package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: raymond
 * CreateTime: 2023/6/15 07:33
 * 题目：给定一个字符串数组，所有字符均为大写字母，
 * 请问，给定的字符串数组是否能通过更换数组中元素的顺序，
 * 从而首尾相连，形成一个环，环上相邻字符串首尾衔接的字符相同。
 * 字符串大概有 10W 个，要求速度尽可能的快。
 * 比如 CAT, TIGER, RPC
 * PDD-Temu-2
 */
public class CircularArray {

    public static void main(String[] args) {
        String[] strings = {"CAT", "TIGER", "RPC"};
        System.out.println("canFormCircularArray(strings) = " + canFormCircularArray(strings));
        System.out.println("canFormCircularArrayII(strings) = " + canFormCircularArrayII(strings));
        String[] strings2 = {"CART", "TEST"};
        System.out.println("canFormCircularArray(strings2) = " + canFormCircularArray(strings2));
        System.out.println("canFormCircularArrayII(strings2) = " + canFormCircularArrayII(strings2));
    }

    public static boolean canFormCircularArray(String[] strings) {
        if (Objects.isNull(strings) || strings.length == 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (String string : strings) {
            char firstChar = string.charAt(0);
            map.put(firstChar, map.getOrDefault(firstChar, 0) + 1);
            char lastChar = string.charAt(string.length() - 1);
            map.put(lastChar, map.getOrDefault(lastChar, 0) + 1);
        }

        for (int count : map.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 手动哈希表统计字符串首位字符出现的个数，并判断每个字符是否为偶数。
     */
    public static boolean canFormCircularArrayII(String[] strings) {
        if (Objects.isNull(strings) || strings.length == 0) {
            return false;
        }
        int[] map = new int[26];
        for (String string : strings) {
            char firstChar = string.charAt(0);
            map[firstChar - 'A']++;
            char lastChar = string.charAt(string.length() - 1);
            map[lastChar - 'A']++;
        }

        for (int count : map) {
            if ((count & 1) == 1) {
                return false;
            }
        }

        return true;
    }
}
