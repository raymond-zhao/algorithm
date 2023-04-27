package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-25 14:01
 * <a href="https://leetcode.cn/problems/rotate-image/">...</a>
 * 48. 旋转图像
 */
public class P48RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // rotate(matrix);
        rotateV2(matrix);
        AlgoUtils.printMatrix(matrix);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < n / 2; ++row) {
            for (int col = 0; col < (n + 1) / 2; ++col) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = temp;
            }
        }
    }

    /**
     * 复制
     * newMatrix[i][j] = matrix[j][n-i-1]
     */
    public static void rotateV2(int[][] matrix) {
        int n = matrix.length;
        int[][] tempMatrix = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                tempMatrix[col][n - row - 1] = matrix[row][col];
            }
        }

        for (int row = 0; row < n; row++) {
            System.arraycopy(tempMatrix[row], 0, matrix[row], 0, n);
        }
    }
}
