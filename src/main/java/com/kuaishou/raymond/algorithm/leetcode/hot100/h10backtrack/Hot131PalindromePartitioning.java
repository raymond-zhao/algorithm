package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 08:22
 * 题目：<a href="https://leetcode.cn/problems/palindrome-partitioning/?envType=study-plan-v2&id=top-100-liked">131. 分割回文串</a>
 */
public class Hot131PalindromePartitioning {

    private List<List<String>> palindromes;

    private Deque<String> palindrome;

    public List<List<String>> partition(String s) {
        palindromes = new ArrayList<>();
        palindrome = new ArrayDeque<>();

        backtrack(s, 0);

        return palindromes;
    }

    private void backtrack(String s, int cuttingIdx) {
        if (cuttingIdx >= s.length()) {
            palindromes.add(new ArrayList<>(palindrome));
            return;
        }
        // 罗列所有选择：枚举所有子串
        for (int i = cuttingIdx; i < s.length(); i++) {
            if (!isPalindrome(s, cuttingIdx, i)) {
                continue;
            }
            palindrome.addLast(s.substring(cuttingIdx, i + 1));
            backtrack(s, i + 1);
            palindrome.removeLast();
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
