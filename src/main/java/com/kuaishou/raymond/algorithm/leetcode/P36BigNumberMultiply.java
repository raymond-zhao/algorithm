package com.kuaishou.raymond.algorithm.leetcode;

import static com.kuaishou.raymond.algorithm.leetcode.P415BigNumberAdd.addStrings;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-29 11:52
 * 大数相乘
 */
public class P36BigNumberMultiply {

    public static void main(String[] args) {
        String num1 = "22";
        String num2 = "5";
        String num3 = "123";
        String num4 = "456";
        System.out.println("multiply(num1, num2) = " + multiply(num1, num2));
        System.out.println("multiply(num3, num4) = " + multiply(num3, num4));
    }

    /**
     * 模拟竖式乘法计算乘积
     * 1. 从右向左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果；
     * 2. 将每次得到的结果累加；
     * 3. 本题中，乘数是 num2，被乘数是 num1；
     * 4. num2 除最低位之外，其余的每一位的运算结果都需要补 0。
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String result = "0";
        int m = num1.length();
        int n = num2.length();

        for (int i = n - 1; i >= 0; i--) {
            StringBuilder builder = new StringBuilder();
            int carry = 0;

            // 补 0
            builder.append("0".repeat(Math.max(0, n - 1 - i)));

            // 被乘数乘以乘数的当前数字
            int multiplier = num2.charAt(i) - '0';
            // 从右向左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果；
            for (int k = m - 1; k >= 0; k--) {
                int multiplicand = num1.charAt(k) - '0';
                int product = multiplicand * multiplier + carry;
                builder.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) {
                builder.append(carry);
            }
            result = addStrings(result, builder.reverse().toString());
        }

        return result;
    }
}
