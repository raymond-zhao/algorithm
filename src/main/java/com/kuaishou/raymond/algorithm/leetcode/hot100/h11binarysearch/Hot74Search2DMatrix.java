package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

import java.util.Arrays;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 09:40
 * 题目：<a href="https://leetcode.cn/problems/search-a-2d-matrix/?envType=study-plan-v2&id=top-100-liked">74. 搜索二维矩阵</a>
 * 相似题目：
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/?envType=study-plan-v2&id=top-100-liked">240. 搜索二维矩阵 II</a>
 * {@link com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix.Hot240SearchMatrixII}
 */
public class Hot74Search2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 120;
        System.out.println("searchRow(matrix, target) = " + searchRow(matrix, target));
        System.out.println("searchMatrix(matrix, target) = " + searchMatrix(matrix, target));
    }

    /**
     * 本题与{@link com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix.Hot240SearchMatrixII}相比，
     * 除去每行有序、每列有序之外，还多了每行的第一个元素大于上一行的最后一个元素这个条件。
     * 直观上想有以下几种方法：
     * 0. 暴力搜索，时间复杂度O(mn)
     * 1. 在每行或者每列进行一次二分，时间复杂度为O(mlogn)或者O(nlogm)
     * 2. 使用抽象树的方式，从左下角或者右上角开始搜索，每次缩小一行或一列，时间复杂度为O(m+n)
     * 3. 同时在行列之间进行二分，先在行中二分，再在列中二分，时间复杂度为 O(logm + logn)=O(logmn)
     * 以上三种方法中，第三种方法是最高效的，我们选择这种方式。
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 1. 在第一列中二分，找到大于 target 的第一个元素所在的行 row，则要搜索的行索引为 row-1。
        int rowIdx = searchRow(matrix, target);
        if (rowIdx < 0) {
            return false;
        }
        int colIdx = searchCol(matrix[rowIdx], target);
        return colIdx >= 0;
    }

    private static int searchCol(int[] row, int target) {
        return Arrays.binarySearch(row, target);
    }

    /**
     * 在第一列中找到最后一个不大于(小于等于) target 的元素所在的行
     */
    private static int searchRow(int[][] matrix, int target) {
        int top = 0;
        int bottom = matrix.length - 1;
        while (top <= bottom) {
            int middle = (top + bottom) >>> 1;
            if (matrix[middle][0] == target) {
                return middle;
            } else if (matrix[middle][0] < target) {
                top = middle + 1;
            } else {
                bottom = middle - 1;
            }
        }
        return bottom;
    }

}
