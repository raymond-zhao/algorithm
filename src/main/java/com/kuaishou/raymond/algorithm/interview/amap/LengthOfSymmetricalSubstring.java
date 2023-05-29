package com.kuaishou.raymond.algorithm.interview.amap;

/**
 * Author: raymond
 * CreateTime: 2023/5/29 23:22
 * 题目：
 * 输入一个字符串，输出该字符串中对称的子字符串的最大长度。比如输入字符串“google”，
 * 由于该字符串里最长的对称子字符串是“goog”，因此输出4。
 * 面试官：浩宸
 */
public class LengthOfSymmetricalSubstring {

    public static void main(String[] args) {
        System.out.println("maxSymmetricalSubstringLength(\"google\") = " + maxSymmetricalSubstringLength("google"));
    }

    public static int maxSymmetricalSubstringLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        // 状态定义：dp[j][i] 表示以子串 j..i 是否是对称子串
        boolean[][] dp = new boolean[len][len];
        // 初始条件：每个字符的最长对称子串的长度至少为 1
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    // 如果 s[i] != s[j]，判断 s[i+1, j-1] 是否是对称串
                    // 比如 google 中的 goo，
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 子串的首尾字符相同，则 i..j 是否对称取决于 (i+1)..(j-1)
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                }
            }
        }

        return maxLength;
    }

}
