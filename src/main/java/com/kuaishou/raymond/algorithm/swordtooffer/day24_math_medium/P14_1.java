package com.kuaishou.raymond.algorithm.swordtooffer.day24_math_medium;

/**
 * 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m，n 都是整数，n > 1 且 m > 1）,
 * 每段绳子的长度记为 k[0], k[1], k[2]...k[m-1], 求 k[0]xk[1]x...x k[m-1] 的最大乘积是多少。
 * 如绳子长度 n=8, 剪成 2，3，3 三段，得到最大乘积为 18.
 * n \in [2, 58]
 */
public class P14_1 {

    public static void main(String[] args) {
        int n_1 = 2; // 1
        int n_2 = 10; // 36
        System.out.println("cuttingRope(2) = " + cuttingRope(2));
        System.out.println("cuttingRope(10) = " + cuttingRope(10));
    }

    /**
     * 驻点 x=3，余数 remainder 有三种情况：
     * 0：刚好整除，返回 3^quotient;
     * 1：余 1，把 4 拆成 3x1 不如拆成 2x2 大，所以返回 3^(quotient-1) x 4;
     * 2：余 2，返回 3^quotient x 2.
     */
    public static int cuttingRope(int n) {
        if (n < 3) {
            return n - 1;
        }
        int quotient = n / 3; // 商
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }
}
