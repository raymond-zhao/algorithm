package com.kuaishou.raymond.algorithm.sword.to.offer1.day29_dp_hard_star;

/**
 * 丑数
 * 我们把只包含质因子 2，3，5 的数称作丑数（Ugly Number），求按从小到大的顺序的第 n 个丑数。
 * n = 10
 * res = 12
 * 1,2,3,4,5,6,8,9,10,12
 * ---
 * 1. 是丑数，n 不超过 1690.
 */
public class P49 {

    public static void main(String[] args) {
        System.out.println("nthUglyNumber(10) = " + nthUglyNumber(10));
    }

    /**
     * dp[i] 表示第 i+1 个丑数
     */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }
}
