package com.kuaishou.raymond.algorithm.sword.to.offer1.day1_stack_and_queue_easy;

import java.util.*;

public class P30MinStack {

    /** initialize your data structure here. */
    private final Queue<Integer> queue;

    private final Deque<Integer> stack;

    public P30MinStack() {
        queue = new PriorityQueue<>();
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.offer(x);
        stack.push(x);
    }

    public void pop() {
        Integer pop = stack.pop();
        queue.remove(pop);
    }

    public int top() {
        return stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
    }

    public int min() {
        return queue.isEmpty() ? Integer.MAX_VALUE : queue.peek();
    }

    public static void main(String[] args) {
        P30MinStack minStack = new P30MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.min();
        System.out.println("min = " + min);
        minStack.pop(); // -3
        minStack.top(); // 0
        minStack.min(); // -2

        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(-2);
        queue.offer(0);
        queue.offer(-3);
        Integer peek = queue.peek();
        System.out.println("peek = " + peek);
        queue.poll();
        Integer peek1 = queue.peek();
        System.out.println("peek1 = " + peek1);
    }

}
