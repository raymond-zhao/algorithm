package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 14:14
 * 判断一个 9X9 的数独是否有效
 * 有效的条件：
 * - 数字 1~9 在每一行只能出现一次
 * - 数字 1~9 在每一列只能出现一次
 * - 数字 1~9 在每个 3x3 的空格内只能出现一次
 */
public class P36ValidSudoKu {

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
        P36ValidSudoKu p36 = new P36ValidSudoKu();
        System.out.println("p36.isValidSudoKu(board) = " + p36.isValidSudoKu(board));
    }

    public boolean isValidSudoKu(char[][] board) {
        // 用于存储每一行中 1~9 出现的次数
        int[][] rows = new int[9][9];
        // 用于存储每一列中 1~9 出现的次数
        int[][] cols = new int[9][9];
        // 用于存储每一个 3x3 的九宫格中 1~9 出现的次数
        int[][][] subBoards = new int[3][3][9];
        // 遍历数独，填写辅助数组。
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 当前遍历到的字符所表示的数字
                char c = board[i][j];
                if (c != '.') {
                    int idx = c - '0' - 1; // 用棋盘中的数字 -1 后做索引
                    rows[i][idx]++;
                    cols[j][idx]++;
                    subBoards[i / 3][j / 3][idx]++;
                    if (rows[i][idx] > 1 || cols[j][idx] > 1 || subBoards[i / 3][j / 3][idx] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
