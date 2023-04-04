package com.kuaishou.raymond.algorithm.math;

import java.math.BigDecimal;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-04 14:27
 * 给定两个非常大的正整数（超过 Integer 的表示范围），计算它们的差。
 */
public class BigNumberSubtract {

    public static void main(String[] args) {
        String s1 = "1";
        String s3 = "998";
        String s2 = "9800000000000000000000000000000000";
        System.out.println("subtractV3(s1, s2) = " + subtractString(s2, s1));
        System.out.println("subtractV3(s1, s2) = " + subtractString(s3, s1));
    }

    /**
     * 大数相减
     *
     * @param subtrahend 被减数
     * @param minuend 减数
     * @return 差
     */
    public static String subtractUsingJDK(String subtrahend, String minuend) {
        return new BigDecimal(subtrahend).subtract(new BigDecimal(minuend)).toString();
    }

    /**
     * 大数相减：被减数大于减数，直接作差即可。
     *
     * @param subtrahend 被减数
     * @param minuend 减数
     * @return 差
     */
    public static String subtractString(String subtrahend, String minuend) {
        char sign = '+';

        // 如果 subtrahend 不大于 minuend，即 subtrahend <= minuend，交换 subtrahend, minuend 的值。
        if (lt(subtrahend, minuend)) {
            sign = '-';
            String team = minuend;
            minuend = subtrahend;
            subtrahend = team;
        }

        int borrow = 0;
        int idxM = subtrahend.length() - 1;
        int idxN = minuend.length() - 1;
        StringBuilder builder = new StringBuilder();

        while (idxM >= 0 || idxN >= 0) {
            // 从个位开始相减
            int digitM = idxM >= 0 ? subtrahend.charAt(idxM--) - '0' : 0;
            int digitN = idxN >= 0 ? minuend.charAt(idxN--) - '0' : 0;

            int num = digitM - digitN - borrow;

            borrow = 0;

            if (num < 0) {
                borrow = 1;
                num += 10;
            }
            builder.append(num);
        }

        builder.reverse();
        int index = 0;
        while (index < builder.length() && builder.charAt(index) == '0') {
            index++;
        }

        if (index == builder.length()) {
            return "0";
        }

        if (sign == '+') {
            return builder.substring(index);
        }

        return sign + builder.substring(index);
    }

    public static boolean lt(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return true;
        } else if (num1.length() > num2.length()) {
            return false;
        } else {
            return num1.compareTo(num2) < 0;
        }
    }

}
