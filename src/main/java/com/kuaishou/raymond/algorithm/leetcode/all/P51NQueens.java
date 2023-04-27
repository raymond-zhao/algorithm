package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-06 17:48
 */
public class P51NQueens {

    public static void main(String[] args) {
        List<List<String>> data = solveNQueens(4);
        System.out.println("data.size() = " + data.size());
        data.forEach(System.out::println);
    }

    private static List<List<String>> data;

    private static int rowNum;

    public static List<List<String>> solveNQueens(int n) {
        data = new ArrayList<>();
        rowNum = n;

        // 生成棋盘
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // 从第 0 行开始填写棋盘
        backtrack(board, 0);

        return data;
    }

    private static void backtrack(char[][] board, int row) {
        if (row == rowNum) {
            // 添加棋盘
            List<String> chessboard = new ArrayList<>();
            for (char[] r : board) {
                chessboard.add(String.valueOf(r));
            }
            data.add(chessboard);
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
