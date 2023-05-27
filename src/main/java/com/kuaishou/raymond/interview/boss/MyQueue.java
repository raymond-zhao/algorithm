package com.kuaishou.raymond.interview.boss;

import java.util.NoSuchElementException;

/**
 * Author: raymond
 * CreateTime: 2023/5/26 19:15
 * 题目：
 */
public class MyQueue {

    private int size;

    /**
     * 数据栈
     */
    private MyStack data;

    /**
     * 辅助栈
     */
    private MyStack helper;

    public MyQueue(int size) {
        this.size = size;
        data = new MyStack(size);
        helper = new MyStack(size);
    }

    /**
     * 队列入队
     */
    public void offer(int num) {
        // 数据
        data.push(num);
    }

    /**
     * 出队
     */
    public int poll() {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (!helper.isEmpty()) {
            return helper.pop();
        }
        // 数据栈非空，将数据栈中的数据压入辅助栈。
        while (Boolean.FALSE.equals(data.isEmpty())) {
            int element = data.pop();
            helper.push(element);
        }
        return helper.top();
    }
}
