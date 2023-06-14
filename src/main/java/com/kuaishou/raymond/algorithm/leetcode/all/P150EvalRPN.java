package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/6/14 08:06
 * 题目：<a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/">150. 逆波兰表达式求值</a>
 * - Medium
 */
public class P150EvalRPN {

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if ("+".equals(token)) {
                int operator1 = stack.pop();
                int operator2 = stack.pop();
                stack.push(operator1 + operator2);
            } else if ("-".equals(token)) {
                int operator1 = stack.pop();
                int operator2 = stack.pop();
                stack.push(operator2 - operator1);
            } else if ("*".equals(token)) {
                int operator1 = stack.pop();
                int operator2 = stack.pop();
                stack.push(operator1 * operator2);
            } else if ("/".equals(token)) {
                int operator1 = stack.pop();
                int operator2 = stack.pop();
                stack.push(operator2 / operator1);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
