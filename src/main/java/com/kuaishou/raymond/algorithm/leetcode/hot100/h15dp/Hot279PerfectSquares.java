package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 09:09
 * 题目：<a href="https://leetcode.cn/problems/perfect-squares/?envType=study-plan-v2&id=top-100-liked">279. 完全平方数</a>
 * - Medium
 * - 动态规划
 * - 四平方和定理
 */
public class Hot279PerfectSquares {

    public static void main(String[] args) {
        System.out.println("numSquares(13) = " + numSquares(13));
        System.out.println("numSquaresII(13) = " + numSquaresII(13));
    }

    /**
     * 完全背包，先循环可选数字，再循环背包容量，判断如果使用当前数字，装满背包所需要的数字个数。
     * 时间复杂度：O(n^1.5)
     * 核心思想：在每次只能选择一件物品（完全平方数）的条件下，分别填充数字 [1..n] 需要的最少平方数。
     */
    public static int numSquaresII(int n) {
        // 计算所有可选物品：1, 4, 9, 16....
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        // 状态定义：dp[i] 表示组成数字 i 时所需要的最少完全平方数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        // 基准条件：使用 1, 4, 9, 16... 组成数字 0 的方案只有 0 种
        dp[0] = 0;
        // 状态转移
        for (int square : squares) {
            // 第一层 for 循环可选的物品
            for (int j = square; j <= n; j++) {
                // 第二层 for 循环容量
                // dp[j]: 不选择当前物品
                // dp[j-square]: 选择当前物品
                dp[j] = Math.min(dp[j], dp[j - square] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 完全背包，先循环背包容量，再循环可选数字，依次用可选数字去更新背包容量。
     * 时间复杂度：O(n^1.5)
     * 核心思想：在容量（数字 num）一定的条件下，依次使用所有可选择物品（平方数的根 root）去填充到目标值 num。
     */
    public static int numSquares(int n) {
        // 状态定义：dp[i] 表示组成数字 i 所需要的最少完全平方数
        int[] dp = new int[n + 1];
        // 基准条件：使用非零的完全平方数组成数字 0 所需的完全平方数个数为 0
        dp[0] = 0;
        // 状态转移：在固定数字 i 的条件下，循环检验所需要的完全平方数的个数。
        for (int num = 1; num <= n; num++) {
            dp[num] = n + 1;
            // 外层循环遍历容量
            for (int root = 1; root * root <= num; root++) {
                // 内层循环遍历平方根
                // 在组合数字 num 时，针对平方根 root，可以「选」，也可以「不选」。
                // 如果选，则组成数字 num 的最小平方数为 dp[num - root * root] + 1，
                // 如果不选，则为 dp[num]。
                dp[num] = Math.min(dp[num], 1 + dp[num - root * root]);
            }
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
