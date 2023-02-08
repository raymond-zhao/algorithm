package com.kuaishou.raymond.algorithm.swordtooffer.day9_dp_medium;

/**
 * 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 */
public class P47 {

    public static void main(String[] args) {
        int[][] presents = {
                {1, 3, 7},
                {1, 5, 1},
                {4, 2, 1}
        };
        P47 p47 = new P47();
        System.out.println("p47.maxValue(presents) = " + p47.maxValue(presents));
        System.out.println("p47.maxValue2(presents) = " + p47.maxValue2(presents));
    }

    /**
     * 动态规划
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // 初始化第一行，第一行只能从左向右前进。
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 初始化第一列，第一列只能从上向下前进。
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 原地修改
     */
    public int maxValue2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 更新第一行
        for (int i = 1; i < n; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }

        // 更新第一列
        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.max(grid[i - 1][j] + grid[i][j], grid[i][j - 1] + grid[i][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
