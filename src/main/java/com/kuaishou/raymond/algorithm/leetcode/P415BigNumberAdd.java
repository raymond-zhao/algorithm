package com.kuaishou.raymond.algorithm.leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-29 12:55
 * 大数相加
 */
public class P415BigNumberAdd {

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "199";
        String num3 = "9999999999999999";
        String num4 = "834719934878219838199128317837821319371893812319831";
        String num5 = "7412834321642714621199128317837821319371893812319831834719934878219838199128317837821319371893812319831";

        BigInteger bigInteger4 = new BigInteger(num4);
        BigInteger bigInteger5 = new BigInteger(num5);
        BigDecimal bigDecimal4 = new BigDecimal(num4);
        BigDecimal bigDecimal5 = new BigDecimal(num5);

        System.out.println("bigDecimal4.add(bigDecimal5) = " + bigDecimal4.add(bigDecimal5));

        System.out.println("bigInteger4.add(bigInteger5) = " + bigInteger4.add(bigInteger5));
        System.out.println("bigInteger4.subtract(bigInteger5) = " + bigInteger4.subtract(bigInteger5));
        System.out.println("bigInteger4.multiply(bigInteger5) = " + bigInteger4.multiply(bigInteger5));
        System.out.println("bigInteger5.divide(bigInteger4) = " + bigInteger5.divide(bigInteger4));

        System.out.println("addStrings(num1, num2) = " + addStrings(num4, num5));
        System.out.println("addStrings(num1, num3) = " + addStrings(num1, num3));

    }

    public String bigIntegerOperation(String num1, String num2, BiFunction<BigInteger, BigInteger, String> function) {
        BigInteger n1 = new BigInteger(num1);
        BigInteger n2 = new BigInteger(num2);
        return function.apply(n1, n2);
    }

    /***
     * 1. 从 num1 与 num2 的个位起，逐个相加，并加上进位 carry：num1[m] + num2[n] + carry = sum
     * 2. sum % 10 = 和的个位数字，添加到 builder 中，
     * 3. sum / 10 = 下一次的进位
     */
    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int carry = 0;
        int m = num1.length();
        int n = num2.length();
        int idx1 = m - 1;
        int idx2 = n - 1;

        StringBuilder builder = new StringBuilder();
        while (idx1 >= 0 || idx2 >= 0) {
            int numStr1 = idx1 < 0 ? 0 : Integer.parseInt(String.valueOf(num1.charAt(idx1--)));
            int numStr2 = idx2 < 0 ? 0 : Integer.parseInt(String.valueOf(num2.charAt(idx2--)));
            int sum = numStr1 + numStr2 + carry;
            builder.append(sum % 10);
            carry = sum / 10;
        }

        if (carry == 1) {
            builder.append(carry);
        }

        return builder.reverse().toString();
    }
}
