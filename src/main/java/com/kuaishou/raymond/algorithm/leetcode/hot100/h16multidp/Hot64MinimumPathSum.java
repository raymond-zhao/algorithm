package com.kuaishou.raymond.algorithm.leetcode.hot100.h16multidp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 23:05
 * 题目：<a href="https://leetcode.cn/problems/minimum-path-sum/?envType=study-plan-v2&id=top-100-liked">64. 最小路径和</a>
 * - Medium
 */
public class Hot64MinimumPathSum {

    /**
     * 为了节省空间，进行原地修改。
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // grid[i][j]：到达 (i, j) 时的最小路径和
        // 初始化第一行
        for (int col = 1; col < n; col++) {
            grid[0][col] += grid[0][col - 1];
        }
        // 初始化第一列
        for (int row = 1; row < m; row++) {
            grid[row][0] += grid[row - 1][0];
        }
        // 状态转移
        for (int row = 1; row < m; ++row) {
            for (int col = 1; col < n; ++col) {
                grid[row][col] += Math.min(grid[row - 1][col], grid[row][col - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

}
