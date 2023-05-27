package com.kuaishou.raymond.algorithm.interview.boss;

import java.util.NoSuchElementException;

/**
 * Author: raymond
 * CreateTime: 2023/5/26 19:05
 * 题目：
 * 1. 使用数组实现栈，实现入栈与出栈功能。
 * 2. 使用已实现的栈实现队列
 */
public class MyStack {

    /**
     * 栈中实际存储的元素数量
     */
    private int size;

    /**
     * 栈的容量
     */
    private final int capacity;

    /**
     * 栈中存储的具体数据
     */
    private final int[] data;

    public MyStack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        data = new int[capacity];
    }

    /**
     * 向栈中加入元素
     *
     * @param num 待添加元素
     */
    public void push(int num) {
        if (this.size == capacity) {
            throw new UnsupportedOperationException("This stack has reached its capacity.");
        }
        // 向栈中添加元素
        data[++size] = num;
    }

    /**
     * 出栈，返回栈中的头部元素。
     * 先进后出，后进先出，所以出栈时返回的时数组尾部的元素。
     */
    public int pop() {
        // 出栈前首先判断是否栈空
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int element = data[size];
        size--;
        return element;
    }

    /**
     * 获取栈顶元素
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[size];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
