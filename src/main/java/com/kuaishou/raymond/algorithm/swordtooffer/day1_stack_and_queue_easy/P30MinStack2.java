package com.kuaishou.raymond.algorithm.swordtooffer.day1_stack_and_queue_easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class P30MinStack2 {

    /** initialize your data structure here. */

    private final Deque<Integer> stack;

    private final Deque<Integer> stackHelper;

    public P30MinStack2() {
        stack = new ArrayDeque<>();
        stackHelper = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (stackHelper.isEmpty() || x <= stackHelper.peek()) {
            stackHelper.push(x);
        }
    }

    public void pop() {
        if (!stack.isEmpty() && stack.pop().equals(stackHelper.peek())) {
            stackHelper.pop();
        }
    }

    public int top() {
        return stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
    }

    public int min() {
        return stackHelper.isEmpty() ? Integer.MAX_VALUE : stackHelper.peek();
    }

    public static void main(String[] args) {
        P30MinStack2 minStack = new P30MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.min();
        System.out.println("min = " + min);
        minStack.pop(); // -3
        minStack.top(); // 0
        minStack.min(); // -2
    }

}
