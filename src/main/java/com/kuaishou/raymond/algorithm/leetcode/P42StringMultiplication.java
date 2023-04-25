package com.kuaishou.raymond.algorithm.leetcode;

import java.math.BigInteger;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-24 14:28
 * <a href="https://leetcode.cn/problems/multiply-strings/">...</a>
 * 43. 字符串相乘/大数相乘
 */
public class P42StringMultiplication {

    public static void main(String[] args) {
        String num1 = "24357";
        String num2 = "937101282";
        System.out.println("new BigInteger(num1).multiply(new BigInteger(num2)) = " + new BigInteger(num1).multiply(new BigInteger(num2)));
        System.out.println("multiply(num1, num2) = " + multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("乘数与被乘数不可为空，请检查参数是否正常。");
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        // 从个位开始相乘
        // 进位是一定要有的
        String data = "0";
        for (int num1Idx = num1.length() - 1; num1Idx >= 0; num1Idx--) {
            StringBuilder sum = new StringBuilder();
            int carry = 0;
            // 需要在低位正序填充 0：从 num1 的倒数第二位到 num1Idx。
            sum.append("0".repeat(Math.max(0, num1.length() - 1 - num1Idx)));

            int digit1 = num1.charAt(num1Idx) - '0';
            for (int num2Idx = num2.length() - 1; num2Idx >= 0; num2Idx--) {
                int digit2 = num2.charAt(num2Idx) - '0';
                int product = digit1 * digit2 + carry;

                sum.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) {
                sum.append(carry);
            }
            // sum 中的值是正序填充的，所以在求和前需要先 reverse。
            data = addTwoStrings(data, sum.reverse().toString());
        }
        return data;
    }

    public static String addTwoStrings(String num1, String num2) {
        return new BigInteger(num1).add(new BigInteger(num2)).toString();
    }
}
