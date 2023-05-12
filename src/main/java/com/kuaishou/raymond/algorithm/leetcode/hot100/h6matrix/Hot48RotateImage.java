package com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix;

/**
 * <a href="https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&id=top-100-liked">48. 旋转图像</a>
 * - 找规律
 */
public class Hot48RotateImage {

    /**
     * 转移方程：newMatrix[i][j] = matrix[j][n - i - 1]
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                newMatrix[col][n - row - 1] = matrix[row][col];
            }
        }

        for (int row = 0; row < n; row++) {
            System.arraycopy(newMatrix[row], 0, matrix[row], 0, n);
        }
    }

}
