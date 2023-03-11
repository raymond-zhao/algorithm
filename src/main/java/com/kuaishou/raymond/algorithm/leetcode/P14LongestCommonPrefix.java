package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-07 13:15
 * 最长公共前缀
 */
public class P14LongestCommonPrefix {

    public static void main(String[] args) {
        String[] words = {"flower", "float", "flow", "flight"};
        System.out.println("longestCommonPrefix(words) = " + longestCommonPrefix(words));
        System.out.println("longestCommonSequence(\"flow\", \"follow\") = " + longestCommonSequence("flow", "follow"));
    }

    /**
     * 最长公共前缀
     */
    public static String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        String prefix = words[0];
        for (int i = 1; i < words.length; i++) {
            prefix = getLongestPrefix(words[i], prefix);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private static String getLongestPrefix(String word, String prefix) {
        int len = Math.min(word.length(), prefix.length());
        int stride = 0;
        for (int i = 0; i < len; i++) {
            if (word.charAt(i) == prefix.charAt(i)) {
                stride++;
            }
        }
        return prefix.substring(0, stride);
    }

    /**
     * 最长公共子序列
     */
    public static int longestCommonSequence(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        int m = str1.length();
        int n = str2.length();
        // dp[i][j] 字符串 s1[0..i] 与 s2[0..j] 的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        // dp[0..m][0] = dp[0][n] = 0
        for (int i = 1; i <= m; i++) {
            char c1 = str1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 最长上升子序列
     */
    public static int longestIncrementalSequence(String str1, String str2) {
        return 0;
    }
}
