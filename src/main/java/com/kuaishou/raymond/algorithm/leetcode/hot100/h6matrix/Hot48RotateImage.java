package com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix;

/**
 * <a href="https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&id=top-100-liked">48. 旋转图像</a>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * - 找规律
 */
public class Hot48RotateImage {

    /**
     * 使用翻转代替交换
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 原地交换四次
     */
    public void rotateUsingSwap(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 转移方程：newMatrix[i][j] = matrix[j][n - i - 1]
     * 时间复杂度: O(n^2)
     * 空间复杂度：O(n^2)
     * 所以，这种方式是不符合要求的。
     */
    public void rotateUsingCopy(int[][] matrix) {
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
