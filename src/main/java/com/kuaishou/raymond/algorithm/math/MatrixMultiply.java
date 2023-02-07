package com.kuaishou.raymond.algorithm.math;

import java.util.Objects;

public class MatrixMultiply {

    public static void main(String[] args) {

        int[][] A = {
                {1, 2, 3, 10},
                {4, 5, 6, 11},
                {7, 8, 9, 12},
                {13, 14, 15, 16}
        };

        int[][] B = {
                {1, 2, 3, 10},
                {4, 5, 6, 11},
                {7, 8, 9, 12},
                {13, 14, 15, 16}
        };

        // printMatrix(squareMatrixMultiplyBF(A, B));

        partition(A);
        partition(B);
    }

    private static void partition(int[][] A) {
        int halfRow = A.length >>> 1;
        int halfCol = A[0].length >>> 1;
        printMatrix(partition(A, 0, halfRow, 0, halfCol));
        printMatrix(partition(A, 0, halfRow, halfCol, A[0].length));
        printMatrix(partition(A, halfCol, A[0].length, 0, halfCol));
        printMatrix(partition(A, halfCol, A[0].length, halfCol, A[0].length));
    }

    public static int[][] squareMatrixMultiplyBF(int[][] A, int[][] B) {
        if (Objects.isNull(A) || Objects.isNull(B) || A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            return new int[0][0];
        }

        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static void printMatrix(int[][] A) {
        for (int[] ints : A) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] partition(int[][] array, int row0, int row1, int col0, int col1) {
        if (Objects.isNull(array) || array.length == 0 || array[0].length == 0) {
            return new int[0][0];
        }

        int row = row1 - row0;
        int col = col1 - col0;
        int[][] res = new int[row][col];

        for (int i = 0; i < row; i++) {
            System.arraycopy(array[row0 + i], col0, res[i], 0, col);
        }

        return res;
    }

}
