package com.kuaishou.raymond.algorithm.sword.to.offer1.day8_dp_easy;

/**
 * 求斐波那契数列的第 N 项
 * 结果对 10e+7 取余
 * <p>
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 */
public class P10_1 {

    public static void main(String[] args) {
        P10_1 p101 = new P10_1();
        int n = 41;
        System.out.println("p101.fib(n) = " + p101.fib(n));
        System.out.println("p101.fib2(10) = " + p101.fib2( n));
    }

    /**
     * F(0) = 1
     * F(1) = 1
     * F(2) = F(0) + F(1) = 2
     * ...
     * F(n) = F(n-1) + F(n-2)
     */
    private int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % (int) (1e9 + 7);
        }
        return dp[n];
    }

    /**
     * 0 1 1 2 3 5 8
     * 0 1 2 3 4 5 6
     */
    private int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int nMinus2 = 0;
        int nMinus1 = 1;
        int cur;
        for (int i = 2; i <= n; i++) {
            cur = (nMinus1 + nMinus2) % (int) (1e9 + 7);
            nMinus2 = nMinus1;
            nMinus1 = cur;
        }
        return nMinus1;
    }
}
