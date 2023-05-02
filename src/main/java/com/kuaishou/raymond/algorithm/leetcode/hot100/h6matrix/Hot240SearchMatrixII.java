package com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 240. 搜索二维矩阵 II
 * 相似题目：{@link com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch.Hot74Search2DMatrix}
 */
public class Hot240SearchMatrixII {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 20;
        System.out.println("searchMatrix(matrix, target) = " + searchMatrix(matrix, target));
    }

    /**
     * 抽象搜索树：O(m+n)
     * 从左下角或右上角开始搜索
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                ++col;
            } else {
                --row;
            }
        }
        return false;
    }

    /**
     * 在每一行中进行二分搜索
     * 时间复杂度：O(mlgn)
     */
    public static boolean searchMatrixII(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (Arrays.binarySearch(row, target) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 暴力法：O(mn)
     */
    public static boolean searchMatrixIII(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (row[col] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
