package com.kuaishou.raymond.algorithm.sword.to.offer1.day5_search_medium;

/**
 * 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右非递减的顺序排序，
 * 每一列都按照从上到下非递减的顺序排序。请完成一个高效的函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * matrix=
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 */
public class P4 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        P4 p4 = new P4();
        System.out.println("p4.findNumberIn2DArray(matrix, 9) = " + p4.findNumberIn2DArray(matrix, 133));
        System.out.println("p4.findNumberIn2DArray2(matrix, 9) = " + p4.findNumberIn2DArray2(matrix, 133));
    }

    /**
     * 大上小右
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        // 从左下角开始向上，向右搜索。
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] < target) {
                // 如果当前值比 target 小，那么它上边的也比 target 小，排除当前列，向右寻找。
                j++;
            } else if (matrix[i][j] > target) {
                // 如果当前值比 target 大，那么它右边的也比 target 大，排除当前行，向上寻找、
                i--;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 对每个行/列进行二分查找
     * 时间复杂度 O(mlgn) 或 O(nlgm)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (binarySearch(row, target) != -1) {
                return true;
            }
        }
        return false;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}
