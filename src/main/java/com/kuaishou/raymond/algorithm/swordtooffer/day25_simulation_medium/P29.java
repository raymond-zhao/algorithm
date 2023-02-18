package com.kuaishou.raymond.algorithm.swordtooffer.day25_simulation_medium;

import java.util.Arrays;

/**
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class P29 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }; // [1,2,3,6,9,8,7,4,5]
        System.out.println("Arrays.toString(spiralOrder(matrix)) = " + Arrays.toString(spiralOrder(matrix)));
    }

    /**
     * 算法流程：
     * - 从第 0 行开始，从第 0 列遍历到第 n-1 列，行变量 row 不变，列表量 col++；
     * - 到第 n-1 列时，列变量 col 不变，行变量 row++；
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] data = new int[m * n];

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        int index = 0;

        while (true) {
            for (int i = left; i <= right; i++) {
                data[index++] = matrix[top][i];
            }
            if (++top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                data[index++] = matrix[i][right];
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                data[index++] = matrix[bottom][i];
            }
            if (--bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                data[index++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }

        return data;
    }
}
