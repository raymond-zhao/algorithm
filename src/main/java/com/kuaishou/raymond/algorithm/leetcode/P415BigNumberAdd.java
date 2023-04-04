package com.kuaishou.raymond.algorithm.leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-29 12:55
 * 大数相加
 */
public class P415BigNumberAdd {

    public static void main(String[] args) {
        String num4 = "834719934878219838199128317837821319371893812319831";
        String num5 = "7412834321642714621199128317837821319371893812319831834719934878219838199128317837821319371893812319831";

        BigInteger bigInteger4 = new BigInteger(num4);
        BigInteger bigInteger5 = new BigInteger(num5);
        BigDecimal bigDecimal4 = new BigDecimal(num4);
        BigDecimal bigDecimal5 = new BigDecimal(num5);

        System.out.println("bigDecimal4.add(bigDecimal5) = " + bigDecimal4.add(bigDecimal5));
        System.out.println("bigInteger4.add(bigInteger5) = " + bigInteger4.add(bigInteger5));

        System.out.println("addStringsV2(num4, num5) = " + addStrings(num4, num5));

    }

    /***
     * 1. 从 augend 与 addend 的个位起，逐个相加，并加上进位 carry：augend[m] + addend[n] + carry = sum
     * 2. sum % 10 = 和的个位数字，添加到 builder 中，
     * 3. sum / 10 = 下一次的进位
     * @param augend 被加数
     * @param addend 加数
     * @return 相加结果
     */
    public static String addStrings(String augend, String addend) {
        if (augend == null || augend.length() == 0) {
            return addend;
        }
        if (addend == null || addend.length() == 0) {
            return augend;
        }

        int m = augend.length();
        int n = addend.length();
        int idxM = m - 1;
        int idxN = n - 1;

        StringBuilder builder = new StringBuilder();
        int carry = 0; // 进位

        while (idxM >= 0 || idxN >= 0) {
            int digitOfM = idxM >= 0 ? augend.charAt(idxM--) - '0' : 0;
            int digitOfN = idxN >= 0 ? addend.charAt(idxN--) - '0' : 0;
            int sum = digitOfM + digitOfN + carry;

            // 将当前位添加到结果集
            builder.append(sum % 10);
            // 计算新的进位
            carry = sum / 10;
        }

        // 检查进位
        if (carry == 1) {
            builder.append(carry);
        }

        return builder.reverse().toString();
    }
}
