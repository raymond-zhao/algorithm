package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 08:30
 * 题目：<a href="https://leetcode.cn/problems/edit-distance/?envType=study-plan-v2&id=top-100-liked">72. 编辑距离</a>
 */
public class Hot72EditDistance {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j] 将 word1 的前 i 个字符转变为 word2 的前 j 个字符所需要的最少操作次数
        int[][] dp = new int[m + 1][n + 1];
        // 第一行
        for (int col = 1; col <= n; col++) {
            // 对 word1 只做插入，从 0 开始插入 j 次后可以变成 word2[1..j]。
            dp[0][col] = col;
        }
        for (int row = 1; row <= m; row++) {
            // 对 word1 只做删除，从 word1[1..row] 删除 row 次之后可变成空字符串。
            dp[row][0] = row;
        }

        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    // 字符相等，不需要对 word1 做任何操作，操作次数与之前相等。
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = 1 + dp[i - 1][j];
                    int delete = 1 + dp[i][j - 1];
                    int replace = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[m][n];
    }

}
