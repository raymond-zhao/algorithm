package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 23:11
 * 题目：<a href="https://leetcode.cn/problems/longest-palindromic-substring/?envType=study-plan-v2&id=top-100-liked">5. 最长回文子串</a>
 */
public class Hot5LongestPalindrome {

    public String longestPalindrome(String s) {
        int n = s.length();
        // dp[i][j]: 字符串 s[]i..j] 是否是回文串
        int maxLength = 1;
        int start = 0;
        boolean[][] dp = new boolean[n][n];

        // 每一个单独的字符都是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        // dp[i][j] = dp[i+1][j-1]
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) { // 第一个错误点
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 如果首尾字符相等，并且 j..i 长度只差小于 3，类似 axa 或 aa 这种，则一定是回文串。
                    if (j - i < 3) { // 第二个错误点
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) { // 第三个错误点
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLength); // 第四个错误点
    }

}
