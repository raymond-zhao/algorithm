package com.kuaishou.raymond.algorithm.leetcode.all;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-27 13:00
 * 正则表达式匹配
 */
public class P10IsMatch {

    public static void main(String[] args) {
        String s = "aaa";
        String p = "ab*ac*a";
        P10IsMatch p10IsMatch = new P10IsMatch();
        System.out.println("p10IsMatch.isMatch(s, p) = " + p10IsMatch.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
