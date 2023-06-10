package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 22:57
 * 题目：<a href="https://leetcode.cn/problems/unique-paths/">62. 不同路径</a>
 * - Medium
 */
public class Hot62UniquePaths {

    public int uniquePaths(int m, int n) {
        // dp[i][j]: 达到 (i,j) 时的不同路径数
        int[][] dp = new int[m][n];
        // 初始化第一行：只有从左往右一种方式
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 初始化第一列：只有从上往下一种方式
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                // 当前方案数 = 从左来的方案数 + 从上来的方案数
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
