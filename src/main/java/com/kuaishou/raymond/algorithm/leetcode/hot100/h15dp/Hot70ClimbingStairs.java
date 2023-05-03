package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 08:32
 * 题目：<a href="https://leetcode.cn/problems/climbing-stairs/">70. 爬楼梯</a>
 */
public class Hot70ClimbingStairs {

    /**
     * 原始 DP，无空间压缩。
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * DP：空间压缩
     */
    public int climbStairsCompressed(int n) {
        if (n <= 2) {
            return n;
        }
        int nMinus2 = 1;
        int nMinus1 = 2;
        for (int i = 3; i <= n; i++) {
            int current = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = current;
        }
        return nMinus1;
    }
}
