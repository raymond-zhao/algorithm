package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/longest-valid-parentheses/">...</a>
 */
public class P32LongestValidParentheses {

    public static void main(String[] args) {
        String s1 = ")()())";
        String s2 = "()(())";
        System.out.println("longestValidParentheses(s2) = " + longestValidParentheses(s1));
        System.out.println("longestValidParenthesesII(s2) = " + longestValidParenthesesII(s1));
        System.out.println("longestValidParenthesesIII(s1) = " + longestValidParenthesesIII(s1));
    }

    /**
     * 没看懂，暂时放弃。
     */
    public static int longestValidParentheses(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        // dp[i]：到 i 为止，最长有效括号的长度。
        int[] dp = new int[s.length()];
        int longestLength = 0;
        for (int idx = 1; idx < s.length(); ++idx) {
            if (s.charAt(idx) == ')') {
                if (s.charAt(idx - 1) == '(') {
                    // 如果当前字符与上一个字符是有效括号对，即 '...()'，那么到 idx 时的最长有效括号长度 +2.
                    dp[idx] = (idx >= 2 ? dp[idx - 2] : 0) + 2;
                } else if (idx - dp[idx - 1] > 0 && s.charAt(idx - dp[idx - 1] - 1) == '(') {
                    // 如果当前字符与上一个字符组成括号对 '...))'
                    // 则倒数第二个 ')' 可能与其之前的一个 '(' 构成有效括号对，而且这个有效对的长度为 dp[i-1]，
                    // 而且这个 '(' 的位置为：当前索引减去上一个有效对的长度，即 idx-dp[idx-1]，
                    // 并且，如果要使 '...))' 有效，前面与其配对的应该是 '((...))'，其中第一个 '(' 的索引为 i-dp[i-1]-1，
                    // 在第一个 '(' 之前的位置就为 i-dp[i-1]-2，因为上一个有效对的长度为 dp[i-dp[i-1]-2]，
                    // 此时的状态转移方程为 dp[i]=上一个有效对的长度+上上个有效对的长度+一个额外的有效对的长度「这里是指 '((...))'」中最外层括号的长度。
                    dp[idx] = dp[idx - 1] + ((idx - dp[idx - 1]) >= 2 ? dp[idx - dp[idx - 1] - 2] : 0) + 2;
                }
                longestLength = Math.max(dp[idx], longestLength);
            }
        }

        return longestLength;
    }

    /**
     * 栈
     */
    public static int longestValidParenthesesII(String s) {
        // 栈底存储最后一个没有被匹配的右括号的索引
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 如果是右括号
                stack.pop();
                if (stack.isEmpty()) {
                    // 如果左括号已全部出栈，此时 push(i)，栈底将会变为：最后一个没有被匹配的右括号的索引。
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    public static int longestValidParenthesesIII(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        // 从左向右扫描
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left < right) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }
}
