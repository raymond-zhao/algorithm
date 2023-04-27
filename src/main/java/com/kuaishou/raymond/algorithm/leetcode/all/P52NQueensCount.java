package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.Arrays;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-07 17:54
 */
public class P52NQueensCount {

    public static void main(String[] args) {
        System.out.println("solveNQueens(8) = " + totalNQueens(8));
    }

    private static int rowNum;

    private static int count;

    public static int totalNQueens(int n) {
        rowNum = n;

        // 生成棋盘
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // 从第 0 行开始填写棋盘
        backtrack(board, 0);

        return count;
    }

    private static void backtrack(char[][] board, int row) {
        if (row == rowNum) {
            // 添加棋盘
            count++;
            return;
        }
        // 罗列所有选择：行中的每一列
        for (int col = 0; col < rowNum; col++) {
            if (placeable(board, row, col)) {
                // 做出选择
                board[row][col] = 'Q';
                // 回溯
                backtrack(board, row + 1);
                // 撤销选择
                board[row][col] = '.';
            }
        }
    }

    private static boolean placeable(char[][] board, int row, int col) {
        // 检查上方
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

}
