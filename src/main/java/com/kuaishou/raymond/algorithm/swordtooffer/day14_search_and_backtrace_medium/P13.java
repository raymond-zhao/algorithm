package com.kuaishou.raymond.algorithm.swordtooffer.day14_search_and_backtrace_medium;

/**
 * 机器人的运动范围
 * mxn 的矩阵，从坐标 (0, 0) 到 (m-1, n-1)。
 * 机器人从坐标 (0, 0) 开始移动，每次可以向左向右，向上向下移动一格。
 * 但是不能移动到矩阵之外，也不能移动到横竖坐标的数位之和大于 k 的方格。
 * 例如，当 k = 18 时，机器人可以进入 (35,37)，因为 3+5+3+7=18, 但不能进入 (35, 38).
 * 问：机器人能够达到多少个格子？
 * 1 <=m,n<=20
 * 0<=k<=20
 */
public class P13 {

    public static void main(String[] args) {
        P13 p13 = new P13();
        System.out.println("p13.movingCount(2, 3, 1) = " + p13.movingCount(2, 3, 1)); // 3
        System.out.println("p13.movingCount(3, 1, 0) = " + p13.movingCount(3, 1, 0)); // 1
    }

    public int movingCount(int m, int n, int k) {
        int movingCount = 0;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int searchingCount = backtrace(matrix, i, j, k);
                movingCount = Math.max(movingCount, searchingCount);
            }
        }
        return movingCount;
    }

    private int backtrace(int[][] matrix, int i, int j, int k) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[0].length) {
            return 0;
        }

        if (k < 0) {
            return 0;
        }

        int i1 = i / 10;
        int i2 = i % 10;
        int j1 = j / 10;
        int j2 = j % 10;

        int sumOfDigits = i1 + i2 + j1 + j2;
        k = k - sumOfDigits;

        return (sumOfDigits <= k ? 1 : 0) + backtrace(matrix, i - 1, j, k) + backtrace(matrix, i + 1, j, k) + backtrace(
                matrix, i, j - 1, k) + backtrace(matrix, i, j + 1, k);
    }
}
