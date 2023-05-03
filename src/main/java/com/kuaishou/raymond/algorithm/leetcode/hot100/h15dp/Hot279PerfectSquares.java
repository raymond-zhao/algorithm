package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 09:09
 * 题目：<a href="https://leetcode.cn/problems/perfect-squares/?envType=study-plan-v2&id=top-100-liked">279. 完全平方数</a>
 */
public class Hot279PerfectSquares {

    public static void main(String[] args) {
        System.out.println("numSquares(13) = " + numSquares(13));
    }

    /**
     * 动态规划
     * 时间复杂度：O(n^1.5)
     * 空间复杂度：O(n)
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int num = 1; num <= n; num++) {
            int minSquares = Integer.MAX_VALUE;
            for (int root = 1; root * root <= num; root++) {
                minSquares = Math.min(minSquares, dp[num - root * root]);
            }
            dp[num] = minSquares + 1;
        }
        return dp[n];
    }

    /**
     * 数学：四平方和定理
     * 时间复杂度：O(n^0.5)
     * 空间复杂度：O(1)
     * 任意一个正整数都可以被表示为至多四个正整数的平方和
     */
    public static int numSquaresMath(int n) {
        if (isPerfectSquare(n)) {
            // 如果 n 刚好是完全平方数，则最少组成数为 1.
            return 1;
        }
        if (checkAnswer4(n)) {
            // 如果满足强约束条件，即 x = 4^k * (8m+7)，则只能由 4 个完全平方数组成。
            return 4;
        }
        // 如果 c = a^2 + b^2，枚举。
        for (int a = 1; a <= n; a++) {
            int b = n - a * a;
            if (isPerfectSquare(b)) {
                return 2;
            }
        }
        return 3;
    }

    /**
     * 判断数字 n 是否是完全平方数
     */
    private static boolean isPerfectSquare(int n) {
        int y = (int) Math.sqrt(n);
        return y * y == n;
    }

    /**
     * 检查是否满足 x = 4^k * (8m + 7)
     */
    private static boolean checkAnswer4(int n) {
        while (n % 4 == 0) {
            // 当 n 时 4 的倍数时，作商。
            n /= 4;
        }
        return n % 8 == 7;
    }
}
