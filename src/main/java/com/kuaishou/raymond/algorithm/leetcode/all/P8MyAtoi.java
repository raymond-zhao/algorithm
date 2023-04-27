package com.kuaishou.raymond.algorithm.leetcode.all;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-14 10:59
 */
public class P8MyAtoi {

    public static void main(String[] args) {
        P8MyAtoi p8 = new P8MyAtoi();
        String s1 = "-91283472332";
        System.out.println("p8.myAtoi(s1) = " + p8.myAtoi(s1));
        System.out.println("(Integer.MIN_VALUE / 10) = " + (Integer.MIN_VALUE / 10));
    }

    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int idx = 0;
        while (idx < chars.length && chars[idx] == ' ') {
            idx++;
        }
        // 如果已经扫描到头了
        if (idx == chars.length) {
            return 0;
        }

        int positive = 1;
        if (chars[idx] == '-') {
            // 如果是负数
            positive = -1;
            idx++;
        } else if (chars[idx] == '+') {
            // 如果是正数
            idx++;
        }
        int num = 0;
        while (idx < chars.length) {
            char c = chars[idx];
            if (c > '9' || c < '0') {
                break;
            }
            if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && c - '0' > Integer.MAX_VALUE % 10) {
                return Integer.MAX_VALUE;
            }
            if (num < Integer.MIN_VALUE / 10 || num == Integer.MIN_VALUE / 10 && c - '0' > Math.abs(Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
            num = num * 10 + positive * (c - '0');
            idx++;
        }
        return num;
    }
}
