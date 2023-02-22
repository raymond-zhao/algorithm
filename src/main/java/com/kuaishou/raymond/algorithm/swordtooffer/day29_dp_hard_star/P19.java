package com.kuaishou.raymond.algorithm.swordtooffer.day29_dp_hard_star;

/**
 * 正则表达式匹配
 */
public class P19 {

    public static void main(String[] args) {
        P19 p19 = new P19();
        String s = "aaa";
        String p1 = "a.a";
        String p2 = "ab*ac*a";
        String p3 = "aa.a";
        System.out.println("p19.isMatch(s, p1) = " + p19.isMatch(s, p1));
        System.out.println("p19.isMatch(s, p2) = " + p19.isMatch(s, p2));
        System.out.println("p19.isMatch(s, p3) = " + p19.isMatch(s, p3));
    }

    /**
     * 状态定义：f[i][j] 表示 s 的第 i 个字符能被 p 的第 j 个字符匹配
     * 状态转移方程：
     * if p[j]=除 ./* 之外的普通字符,
     * - if s[i]=p[j], f[i][j]=f[i-1][j-1];
     * - if s[i]!=p[j], f[i][j]=false;
     * ---
     * if p[j]='.', f[i][j]=f[i-1][j-1];
     * ---
     * if p[j]='*',
     * 如果 p[j-1..j] 匹配了 0 次，即「某个普通字符/. + *」的组合匹配了 0 次，f[i][j]=f[i][j-2];
     * 如果 p[j-1..j] 匹配了 1 次或多次，f[i][j]=f[i-N][j-2]... 这样编程是非常困难的。
     * 所以转换思路：字符加 * 号的组合本质上有两种情况，
     * -「字符+*」组合匹配了 s[i], 则丢弃 s[i], 继续匹配 s[i-1], s[i-2], s[i-3]...
     * -「字符+*」组合没有匹配 s[i]，则丢弃「字符+*」组合，则丢弃这个组合，使用 p[0..j-2] 继续匹配。
     * ---
     * 一个特例，如果 p=.*，则可以匹配任意 s。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }

        return f[m][n];
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
