package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

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
        System.out.println(
                "hot.longestCommonSubsequence(text1, text2) = " + hot.longestCommonSubsequence(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 状态定义：dp[i][j] 表示 text1 [0..i-1] text2[0..j-1] 的最长公共子序列的长度
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
        return dp[m][n];
    }

}
