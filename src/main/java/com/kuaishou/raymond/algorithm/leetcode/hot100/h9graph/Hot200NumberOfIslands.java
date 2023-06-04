package com.kuaishou.raymond.algorithm.leetcode.hot100.h9graph;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 11:29
 * 题目：<a href="https://leetcode.cn/problems/number-of-islands/?envType=study-plan-v2&id=top-100-liked">200. 岛屿数量</a>
 * 解答：<a href="https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/">岛屿类问题的通用解法、DFS 遍历框架</a>
 * - 深度优先搜索
 */
public class Hot200NumberOfIslands {

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    dfs(grid, row, col);
                    ++res;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int row, int col) {
        if (outBoundries(grid, row, col)) {
            return;
        }
        if (!isIsland(grid, row, col)) {
            return;
        }
        // 将访问过的陆地置为 2
        grid[row][col] = '2';
        // 向上下左右四个地方进行深度优先搜索
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    private boolean isIsland(char[][] grid, int row, int col) {
        return grid[row][col] == '1';
    }

    private boolean outBoundries(char[][] grid, int row, int col) {
        return row < 0 || col >= grid[0].length || row >= grid.length || col < 0;
    }
}
