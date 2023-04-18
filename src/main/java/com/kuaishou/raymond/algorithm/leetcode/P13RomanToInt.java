package com.kuaishou.raymond.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P13RomanToInt {

    public static final Map<Character, Integer> MAP = new HashMap<>();

    static {
        MAP.put('I', 1);
        MAP.put('V', 5);
        MAP.put('X', 10);
        MAP.put('L', 50);
        MAP.put('C', 100);
        MAP.put('D', 500);
        MAP.put('M', 1000);
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int data = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer value = MAP.get(s.charAt(i));
            // 如果左边的值小于右边的值，则做减法。
            if (i < s.length() - 1 && value < MAP.get(s.charAt(i + 1))) {
                data -= value;
            } else {
                data += value;
            }
        }
        return data;
    }
}
