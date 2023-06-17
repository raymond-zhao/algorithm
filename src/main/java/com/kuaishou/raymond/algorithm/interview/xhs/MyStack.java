package com.kuaishou.raymond.algorithm.interview.xhs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/6/17 07:55
 * 题目：<a href="https://leetcode.cn/problems/implement-stack-using-queues/">225. 用队列实现栈</a>
 * 1.请你仅使用队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 输入：push1,push2,push3
 * 输出：pop3,top2,pop2,pop1,empty true
 */
public class MyStack {

    /**
     * 数据队列：存储所有数字
     */
    private Deque<Integer> data;

    /**
     * 辅助队列：中转 top 之前的元素
     */
    private Deque<Integer> helper;

    /**
     *
     */
    private int top;

    public MyStack() {
        data = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int x) {
        helper.offer(x);
        top = x;
        while (!data.isEmpty()) {
            // 将数据队列中的数字全部迁移到辅助队列
            helper.offer(data.poll());
        }
        // 交换数据队列与辅助队列
        Deque<Integer> temp = data;
        data = helper;
        helper = temp;
    }

    public int pop() {
        int e = data.poll();
        if (!data.isEmpty()) {
            top = data.peek();
        }
        return e;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return data.isEmpty();
    }
}
