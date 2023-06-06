package com.kuaishou.raymond.algorithm.leetcode.hot100.h12stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 14:24
 * 题目：<a href="https://leetcode.cn/problems/valid-parentheses/?envType=study-plan-v2&id=top-100-liked">20. 有效的括号</a>
 */
public class Hot20ValidParentheses {

    private static final Map<Character, Character> MAP = new HashMap<>();

    static {
        MAP.put(']', '[');
        MAP.put(')', '(');
        MAP.put('}', '{');
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (MAP.containsKey(ch)) {
                // 如果是右括号
                if (stack.isEmpty() || !Objects.equals(stack.peek(), MAP.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                // 如果是左括号，入栈
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
