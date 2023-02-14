package com.kuaishou.raymond.algorithm.swordtooffer.day20_divide_and_conquer;

/**
 * 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
 * ---
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * ---
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * ---
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * ---
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class P16 {

    public static void main(String[] args) {
        P16 p16 = new P16();
        System.out.println("p16.myPow(2, 4) = " + p16.myPow(2, -2));
    }

    /**
     * - 当 x 为正数时：
     *  - 当 n 为偶数时，x^n = x^(n/2) * x^(n/2)
     *  - 当 n 为奇数时，x^n = x^(n/2) * x^(n/2) * x
     * - 当 x 为负数时：
     *  - 当 n 为偶数时，
     * @param x 底
     * @param n 幂
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        long b = n;
        if (b < 0) {
            // 把负幂转为正幂
            x = 1 / x;
            b = -b;
        }

        double res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = res * x;
            }
            x = x * x;
            b = b >> 1;
        }
        return res;
    }
}
