package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.Arrays;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 14:18
 * 通过填充输入 board 中的空格来填写棋盘，得出有效的数独。
 */
public class P37SolveSudoKu {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        P37SolveSudoKu p37 = new P37SolveSudoKu();
        p37.solveSudoku(board);
        for (char[] ch : board) {
            System.out.println("Arrays.toString(ch) = " + Arrays.toString(ch));
        }
    }

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                // 罗列所有选择
                for (char k = '1'; k <= '9'; k++) {
                    if (isValidSudoKu(board, i, j, k)) {
                        // 做出选择
                        board[i][j] = k;
                        // 回溯
                        if (backtrack(board)) {
                            return true;
                        }
                        // 撤销选择
                        board[i][j] = '.';
                    }
                }
                return false; // 九个数字都放完了还未找到，提前返回。
            }
        }
        return true;
    }

    private boolean isValidSudoKu(char[][] board, int row, int col, char digit) {
        // 检查同行是否有重复
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == digit) {
                return false;
            }
        }
        // 检查同列是否有重复
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == digit) {
                return false;
            }
        }
        // 检查小的九宫格里是否有重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }
}
