package com.kuaishou.raymond.algorithm.swordtooffer.day31_math_hard;

/**
 * 统计数字中 1 出现的次数
 * n \in [1, 2^31]
 */
public class P43 {

    public static void main(String[] args) {
        P43 p43 = new P43();
        System.out.println("p43.countDigitOne(12) = " + p43.countDigitOneV2(12));
        System.out.println("p43.countDigitOne(13) = " + p43.countDigitOneV2(13));
        long start = System.currentTimeMillis();
        System.out.println("p43.count(824883294) = " + p43.countDigitOneV2(824883294));
        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));
    }

    public int countDigitOneV2(int n) {
        int mulk = 1; // 代表 10 的幂，即 10^k，以 k=0 开始算。
        int count = 0;
        for (int k = 0; mulk <= n; k++) {
            count += (n / (mulk * 10)) * mulk + Math.min(mulk, Math.max(n % (mulk * 10) - mulk + 1, 0));
            mulk = mulk * 10;
        }
        return count;
    }

    public int countDigitOneV1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += count(i);
        }
        return count;
    }

    /**
     * 统计数字 number 中 1 出现的次数
     */
    private int count(int number) {
        int countOfOne = 0;
        char[] chars = String.valueOf(number).toCharArray();
        for (char c : chars) {
            if (c == '1') {
                countOfOne++;
            }
        }
        return countOfOne;
    }
}
