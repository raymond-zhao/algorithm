package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 23:46
 * 题目：<a href="https://leetcode.cn/problems/longest-common-subsequence/?envType=study-plan-v2&id=top-100-liked">1143. 最长公共子序列</a>
 */
public class Hot1143LongestCommonSubsequences {

    public static void main(String[] args) {
        Hot1143LongestCommonSubsequences hot = new Hot1143LongestCommonSubsequences();
        String text1 = "abcde";
        String text2 = "ace";
        hot.longestCommonSubsequence(text1, text2);
        hot.longestCommonSubsequenceII(text1, text2);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // dp[i][j] 到 text1 长度为 i 并且 text2 长度为 j 时的最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        AlgoUtils.printMatrix(dp);
        return dp[m][n];
    }

    /**
     * 有误
     */
    public int longestCommonSubsequenceII(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // dp[i][j]：text1[0..i] 与 text2[0..j] 最长公共子序列的长度
        int[][] dp = new int[m][n];

        // 初始化第一行：使用 text1[0] 与 text2 每个字符去匹配
        for (int col = 0; col < n; col++) {
            if (text1.charAt(0) == text2.charAt(col)) {
                dp[0][col] = 1;
            }
        }
        // 初始化第一列：使用 text2[0] 与 text1 每个字符去匹配
        for (int row = 0; row < m; row++) {
            if (text2.charAt(0) == text1.charAt(row)) {
                dp[row][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        AlgoUtils.printMatrix(dp);
        return dp[m - 1][n - 1];
    }

}
