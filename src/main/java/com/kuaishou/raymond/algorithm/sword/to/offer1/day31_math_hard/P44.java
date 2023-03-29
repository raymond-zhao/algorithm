package com.kuaishou.raymond.algorithm.sword.to.offer1.day31_math_hard;

/**
 * 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，
 * 第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * n \in [0, 2^31]
 */
public class P44 {

    public static void main(String[] args) {
        P44 p44 = new P44();
        System.out.println("p44.findNthDigit(13) = " + p44.findNthDigit(13));
    }

    /**
     * 求解步骤：
     * 1. 确定 n 所在数字的位数，记为 digit；
     * 2. 确定 n 所在数字，记为 num；
     * 3. 确定 n 是 num 中的哪一数位，并返回结果。
     */
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        // 1. 确定 n 所处的数位（由几位数字组成，是个/十/百/千... 的哪一种）
        while (count < n) {
            n -= count;
            digit += 1; // 1, 2, 3, 4...
            start *= 10; // 1, 10, 100, 1000...
            count = 9 * digit * start; // 9*1*1, 9*2*10, 9*3*100, 9*4*1000...
        }
        // 2. 确定 n 所处数位对应的具体数字，除。
        long num = start + (n - 1) / digit;
        // 3. 确定 n 在所处数字中的具体位数，余。
        int index = (n - 1) % digit;
        return String.valueOf(num).charAt(index) - '0';
    }

}
