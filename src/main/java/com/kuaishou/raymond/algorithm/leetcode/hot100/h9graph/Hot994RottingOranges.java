package com.kuaishou.raymond.algorithm.leetcode.hot100.h9graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 17:36
 * 题目：<a href="https://leetcode.cn/problems/rotting-oranges/?envType=study-plan-v2&id=top-100-liked">994. 腐烂的橘子</a>
 * 解析：<a href="https://leetcode.cn/problems/rotting-oranges/solution/li-qing-si-lu-wei-shi-yao-yong-bfsyi-ji-ru-he-xie-/">
 * 理清思路：为什么用 BFS，以及如何写 BFS 代码（Java/Python）</a>
 */
public class Hot994RottingOranges {

    /**
     * 新鲜的橘子
     */
    private static final int FRESH_ORANGE = 1;

    /**
     * 腐烂的橘子
     */
    private static final int ROTTEN_ORANGE = 2;

    /**
     * 广度优先遍历
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        int freshOranges = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == FRESH_ORANGE) {
                    // 1. 统计新鲜橘子的数量
                    freshOranges++;
                } else if (grid[row][col] == ROTTEN_ORANGE) {
                    // 2. 记录腐烂橘子的位置
                    queue.offer(new int[]{row, col});
                }
            }
        }

        // 橘子腐烂所需要的时间
        int rottingTime = 0;
        while (freshOranges > 0 && !queue.isEmpty()) {
            rottingTime++;
            int rottenOranges = queue.size();
            for (int i = 0; i < rottenOranges; i++) {
                // 腐烂橘子的坐标
                int[] coordinates = queue.poll();
                int row = coordinates[0];
                int col = coordinates[1];
                // 上一行
                if (row - 1 >= 0 && grid[row - 1][col] == FRESH_ORANGE) {
                    --freshOranges;
                    spread(grid, row - 1, col, queue);
                }
                // 下一行
                if (row + 1 < m && grid[row + 1][col] == FRESH_ORANGE) {
                    --freshOranges;
                    spread(grid, row + 1, col, queue);
                }
                // 左一列
                if (col - 1 >= 0 && grid[row][col - 1] == FRESH_ORANGE) {
                    --freshOranges;
                    spread(grid, row, col - 1, queue);
                }
                // 右一列
                if (col + 1 < n && grid[row][col + 1] == FRESH_ORANGE) {
                    --freshOranges;
                    spread(grid, row, col + 1, queue);
                }
            }
        }

        return freshOranges > 0 ? -1 : rottingTime;
    }

    private void spread(int[][] grid, int row, int col, Queue<int[]> queue) {
        grid[row][col] = ROTTEN_ORANGE;
        queue.offer(new int[]{row, col});
    }

}
