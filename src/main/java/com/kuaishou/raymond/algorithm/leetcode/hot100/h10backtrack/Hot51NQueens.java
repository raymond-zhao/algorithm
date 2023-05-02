package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 08:32
 * 题目：<a href="https://leetcode.cn/problems/n-queens/?envType=study-plan-v2&id=top-100-liked">51. N 皇后</a>
 * 相似题目：<a href="https://leetcode.cn/problems/n-queens-ii/">52. N 皇后 II</a>
 */
public class Hot51NQueens {

    private List<List<String>> boards;

    public List<List<String>> solveNQueens(int n) {
        boards = new ArrayList<>();
        // 1. 生成棋盘
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0);
        return boards;
    }

    /**
     * 回溯搜索
     * @param board 待搜索的棋盘
     * @param rowNo 当前搜索到的行的编号，从第 0 行开始搜索。
     */
    private void backtrack(char[][] board, int rowNo) {
        if (rowNo == board.length) {
            List<String> chessboard = new ArrayList<>();
            for (char[] row : board) {
                chessboard.add(String.valueOf(row));
            }
            boards.add(chessboard);
            return;
        }
        // 罗列所有选择：枚举当前行的每一列
        for (int col = 0; col < board[0].length; col++) {
            if (!placeable(board, rowNo, col)) {
                continue;
            }
            board[rowNo][col] = 'Q';
            backtrack(board, rowNo + 1);
            board[rowNo][col] = '.';
        }
    }

    private boolean placeable(char[][] board, int row, int col) {
        // 检查上方
        for (int i = row - 1; i >= 0; i--) {
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
