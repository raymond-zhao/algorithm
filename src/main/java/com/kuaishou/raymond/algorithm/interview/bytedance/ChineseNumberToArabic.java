package com.kuaishou.raymond.algorithm.interview.bytedance;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/5/14 22:41
 * 题目：
 */
public class ChineseNumberToArabic {

    public static void main(String[] args) {
        String s1 = "一千一百零一";
        String s2 = "一千零一";
        String s3 = "九十九";
        String s4 = "十一";
        System.out.println("chineseToArabic(s1) = " + chineseToArabic(s1));
        System.out.println("chineseToArabic(s2) = " + chineseToArabic(s2));
        System.out.println("chineseToArabic(s3) = " + chineseToArabic(s3));
        System.out.println("chineseToArabic(s4) = " + chineseToArabic(s4));
    }

    private static final Map<String, Integer> CHINESE_DIGITS_MAP = new HashMap<>();
    private static final Map<String, Integer> CHINESE_UNITS_MAP = new HashMap<>();

    static {
        CHINESE_DIGITS_MAP.put("零", 0);
        CHINESE_DIGITS_MAP.put("一", 1);
        CHINESE_DIGITS_MAP.put("二", 2);
        CHINESE_DIGITS_MAP.put("三", 3);
        CHINESE_DIGITS_MAP.put("四", 4);
        CHINESE_DIGITS_MAP.put("五", 5);
        CHINESE_DIGITS_MAP.put("六", 6);
        CHINESE_DIGITS_MAP.put("七", 7);
        CHINESE_DIGITS_MAP.put("八", 8);
        CHINESE_DIGITS_MAP.put("九", 9);

        CHINESE_UNITS_MAP.put("十", 10);
        CHINESE_UNITS_MAP.put("百", 100);
        CHINESE_UNITS_MAP.put("千", 1000);
        CHINESE_UNITS_MAP.put("万", 10000);
        CHINESE_UNITS_MAP.put("亿", 100000000);
    }

    /**
     * 中文数字转阿拉伯数字
     */
    public static long chineseToArabic(String s) {
        Deque<Long> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            String chineseNumber = String.valueOf(s.charAt(i));
            if (CHINESE_UNITS_MAP.containsKey(chineseNumber)) {
                // 如果当前字符是中文数字的单位，也就是十、百、千、万、亿。
                long unit = CHINESE_UNITS_MAP.get(chineseNumber);
                long currentNumber = 0;
                // 如果栈顶元素小于当前遍历到的单位，出栈，并累加到当前单位下的结果。
                while (!stack.isEmpty() && stack.peek() < unit) {
                    currentNumber += stack.pop();
                }
                // 特殊处理"十~十九"，因为"十"是个合法的中文数字，而"一十"不是合法中文数字。
                // 而在其他单位"百，千，万"时，前面必须包括基数"零~九"，因为直接说"百"不符合中国人的叫法，"一百"才是正常叫法。
                if (stack.isEmpty() && currentNumber == 0 && "十".equals(chineseNumber)) {
                    stack.push(10L);
                } else {
                    stack.push(currentNumber * unit);
                }
            } else if (CHINESE_DIGITS_MAP.containsKey(chineseNumber)) {
                // 如果当前字符只是普通的数字，也就是零~九。
                int num = CHINESE_DIGITS_MAP.get(chineseNumber);
                stack.push((long) num);
            } else {
                throw new IllegalArgumentException("Illegal chinese number occurred, please be aware.");
            }
        }

        long ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}

