package com.kuaishou.raymond.algorithm.swordtooffer.day14_search_and_backtrace_medium;

/**
 * 矩阵中的路径
 * [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
 * "ABCB"  FALSE
 * ---
 * [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
 * "SEE" TRUE
 */
public class P12 {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        P12 p12 = new P12();
        System.out.println("p12.exist(board, \"ABCCED\") = " + p12.exist(board, "ABCCED"));
        System.out.println("p12.exist(board, \"SEE\") = " + p12.exist(board, "SEE"));
        System.out.println("p12.exist(board, \"ABCB\") = " + p12.exist(board, "ABCB"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (Boolean.TRUE.equals(backtrace(board, i, j, word, 0))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrace(char[][] board, int i, int j, String word, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(k)) {
            return false;
        }

        if (k == word.length() - 1) {
            return true;
        }

         board[i][j] = ' '; // 必须做访问标记，如果不做的话，一个字符可能会被访问多次。
        boolean res = backtrace(board, i + 1, j, word, k + 1)
                || backtrace(board, i - 1, j, word, k + 1)
                || backtrace(board, i, j + 1, word, k + 1)
                || backtrace(board, i, j - 1, word, k + 1);
        board[i][j] = word.charAt(k); // 还原
        return res;
    }
}
