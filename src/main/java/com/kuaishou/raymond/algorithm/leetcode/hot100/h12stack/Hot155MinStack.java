package com.kuaishou.raymond.algorithm.leetcode.hot100.h12stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 14:33
 * 题目：<a href="https://leetcode.cn/problems/min-stack/?envType=study-plan-v2&id=top-100-liked">155. 最小栈</a>
 */
public class Hot155MinStack {

    public static void main(String[] args) {
        Hot155MinStack minStack = new Hot155MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

    /**
     * 用于存储实际元素
     */
    private final Deque<Integer> stack;

    /**
     * 用于存储栈中的最小值
     */
    private final Deque<Integer> helper;

    public Hot155MinStack() {
        stack = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        // 入栈时需要注意辅助栈的判断条件
        if (helper.isEmpty() || val <= helper.peek()) {
            helper.push(val);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (!helper.isEmpty() && Objects.equals(pop, helper.peek())) {
            helper.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return helper.peek();
    }

}
