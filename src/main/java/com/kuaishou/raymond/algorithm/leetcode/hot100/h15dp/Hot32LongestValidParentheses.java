package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 20:56
 * 题目：<a href="https://leetcode.cn/problems/longest-valid-parentheses/?envType=study-plan-v2&id=top-100-liked">32. 最长有效括号</a>
 * - Hard
 */
public class Hot32LongestValidParentheses {

    public static void main(String[] args) {
        String s = ")()())";
        System.out.println("longestValidParenthesesStack(s) = " + longestValidParenthesesStack(s));
    }

    /**
     * 栈
     */
    public static int longestValidParenthesesStack(String s) {
        int maxLength = 0;
        // 栈中存储左括号的下标
        // 栈底元素：已经遍历过的字符中，最后一个没有被匹配的右括号的下标。
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 左括号，入栈。
                stack.push(i);
            } else {
                // 右括号，出栈，计算长度。
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength,  i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    /**
     * 动态规划
     */
    public static int longestValidParentheses(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        // dp[i]：到 i 为止，最长有效括号的长度。
        int[] dp = new int[s.length()];
        int maxLength = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 如果当前字符与前一个字符是有效括号对，即 '...()'，那么到 i 时的最长有效括号长度 +2.
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 如果当前字符与上一个字符组成括号对 '...))'，并且倒数第二个 ')' 可能与其之前的一个 '(' 构成有效括号对，
                    // 并且这个有效对的长度为 dp[i-1]，上一个 '(' 出现的位置为 i-dp[i-1]-1，
                    // 所以当前括号的长度 = 与当前 '..))' 构成的有效长度 + '((..))' 之前的有效括号长度，
                    // 具体的例子可以参考: '...()(())'
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLength = Math.max(dp[i], maxLength);
            }
        }
        return maxLength;
    }
}
