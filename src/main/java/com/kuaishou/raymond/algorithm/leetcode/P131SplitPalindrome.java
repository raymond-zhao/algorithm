package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 */
public class P131SplitPalindrome {

    public static void main(String[] args) {
        String s = "aab"; // [["a","a","b"],["aa","b"]]
        System.out.println("s.substring(0, 0) = " + s.substring(0, 0));
        P131SplitPalindrome p131 = new P131SplitPalindrome();
        System.out.println("p131.partition(s) = " + p131.partition(s));
    }

    private List<List<String>> data;

    private Deque<String> path;

    public List<List<String>> partition(String s) {
        data = new ArrayList<>();
        path = new ArrayDeque<>();

        backtrack(s, 0);

        return data;
    }

    /**
     * @param s 待切割的字符串
     * @param cuttingIdx 切割点
     */
    private void backtrack(String s, int cuttingIdx) {
        if (cuttingIdx >= s.length()) {
            data.add(new ArrayList<>(path));
            return;
        }
        // 罗列所有选择：能够切割出的所有字符串
        for (int i = cuttingIdx; i < s.length(); i++) {
            // chars[cuttingIdx, i] 之间的字符串
            if (!isPalindrome(s, cuttingIdx, i)) {
                continue;
            }
            // 做出选择
            path.addLast(s.substring(cuttingIdx, i + 1));
            // 回溯
            backtrack(s, i + 1);
            // 撤销选择
            path.removeLast();
        }
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
