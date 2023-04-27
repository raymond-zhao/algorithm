package com.kuaishou.raymond.algorithm.leetcode.all;

import static com.kuaishou.raymond.algorithm.leetcode.all.P415BigNumberAdd.addStrings;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        System.out.println("multiplyJDK(num3, num4) = " + multiplyJDK(num3, num4));
        System.out.println("multiplyV2(num3, num4) = " + multiplyV2(num3, num4));
    }

    /**
     * 模拟竖式乘法计算乘积
     * 1. 从右向左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果；
     * 2. 将每次得到的结果累加；
     * 3. 本题中，乘数是 multiplier，被乘数是 multiplicand；
     * 4. multiplier 除最低位之外，其余的每一位的运算结果都需要补 0。
     * @param multiplicand 被乘数
     * @param multiplier 乘数
     * @return 积
     */
    public static String multiply(String multiplicand, String multiplier) {
        if ("0".equals(multiplicand) || "0".equals(multiplier)) {
            return "0";
        }
        String result = "0";
        int m = multiplicand.length();
        int n = multiplier.length();

        for (int i = n - 1; i >= 0; i--) {
            StringBuilder builder = new StringBuilder();
            int carry = 0;

            // 补 0
            builder.append("0".repeat(Math.max(0, n - 1 - i)));

            // 被乘数乘以乘数的当前数字
            int digitOfN = multiplier.charAt(i) - '0';
            // 从右向左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果；
            for (int k = m - 1; k >= 0; k--) {
                int digitOfM = multiplicand.charAt(k) - '0';
                int product = digitOfM * digitOfN + carry;
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

    public static String multiplyJDK(String multiplicand, String multiplier) {
        return new BigDecimal(multiplicand).multiply(new BigDecimal(multiplier)).toString();
    }

    public static String multiplyV2(String multiplicand, String multiplier) {
        if (multiplicand == null || multiplicand.length() == 0) {
            return null;
        }

        if (multiplier == null || multiplier.length() == 0) {
            return null;
        }

        if ("0".equals(multiplicand) || "0".equals(multiplier)) {
            return "0";
        }

        int m = multiplicand.length();
        int n = multiplier.length();

        int carry = 0;
        String data = "0";

        // 使用乘数的每一位去乘以被乘数得到当前积，然后将当前积添加到结果集上。
        for (int idxN = n - 1; idxN >= 0; idxN--) {
            StringBuilder builder = new StringBuilder();
            // 需要在 builder 补上 n-2-idxN + 1 = n-idx-1N 个 0
            builder.append("0".repeat(Math.max(0, n - 1 - idxN)));
            // 开始相乘
            int digitN = multiplier.charAt(idxN) - '0';
            for (int idxM = m - 1; idxM >= 0; idxM--) {
                int digitM = multiplicand.charAt(idxM) - '0';
                int product = digitN * digitM + carry;
                builder.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) {
                builder.append(carry);
            }
            data = new BigInteger(data).add(new BigInteger(builder.reverse().toString())).toString();
        }

        return data;
    }
}
