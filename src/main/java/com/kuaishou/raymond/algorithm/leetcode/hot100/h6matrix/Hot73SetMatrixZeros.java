package com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&id=top-100-liked">73. 矩阵置零</a>
 * - 必须使用原地算法，也就是说不能够使用额外的空间。
 */
public class Hot73SetMatrixZeros {

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(m+n)
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 行数组，记录每一行是否出现了 0。
        boolean[] row = new boolean[m];
        // 列数组，记录每一列是否出现了 0。
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1)
     */
    public void setZeroesII(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 1. 使用标志位记录第一行与第一列是否包含 0
        boolean firstColContainsZero = false;
        for (int row = 0; row < m; row++) {
            if (matrix[row][0] == 0) {
                firstColContainsZero = true;
                break;
            }
        }
        boolean firstRowContainsZero = false;
        for (int col = 0; col < n; col++) {
            if (matrix[0][col] == 0) {
                firstRowContainsZero = true;
                break;
            }
        }
        // 2. 使用 「1行1列」之外的元素来更新「第一行与第一列」
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = matrix[0][col] = 0;
                }
            }
        }
        // 3. 使用更新后的「1行1列」去更新第「row行col列」
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        // 4. 如果第一列一开始就包含 0，将第一行置为置为 0。
        if (firstColContainsZero) {
            for (int row = 0; row < m; row++) {
                matrix[row][0] = 0;
            }
        }
        // 5. 如果第一行一开始就包含 0，将第一列置为 0。
        if (firstRowContainsZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}
