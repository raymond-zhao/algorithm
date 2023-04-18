package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-17 19:21
 */
public class P12IntToRoman {

    public static void main(String[] args) {
        System.out.println("intToRoman(1994) = " + intToRoman(1994));
    }

    protected static final int[] INTS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    protected static final String[] ROMANS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 贪心策略
     */
    public static String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < INTS.length; i++) {
            int value = INTS[i];
            while (num >= value) {
                builder.append(ROMANS[i]);
                num -= value;
            }
            if (num == 0) {
                break;
            }
        }
        return builder.toString();
    }
}
