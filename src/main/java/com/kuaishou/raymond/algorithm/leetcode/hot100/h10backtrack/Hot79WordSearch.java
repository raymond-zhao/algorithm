package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 07:59
 * 题目：<a href="https://leetcode.cn/problems/word-search/?envType=study-plan-v2&id=top-100-liked">79. 单词搜索</a>
 * 相似题目：<a href="https://leetcode.cn/problems/word-search-ii/">212. 单词搜索 II</a>
 */
public class Hot79WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (word.length() > m * n) {
            return false;
        }
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == word.charAt(0)) {
                    boolean found = backtrack(board, row, col, word, 0);
                    if (found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int row, int col, String word, int wordIdx) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || wordIdx > word.length() - 1) {
            return false;
        }
        if (board[row][col] != word.charAt(wordIdx)) {
            return false;
        }
        if (wordIdx == word.length() - 1) {
            return true;
        }
        // 开始搜索，将搜索过的位置置空。
        char ch = board[row][col];
        board[row][col] = ' ';

        boolean res = backtrack(board, row + 1, col, word, wordIdx + 1)
                || backtrack(board, row - 1, col, word, wordIdx + 1)
                || backtrack(board, row, col + 1, word, wordIdx + 1)
                || backtrack(board, row, col - 1, word, wordIdx + 1);

        // 还原
        board[row][col] = ch;
        return res;
    }
}
