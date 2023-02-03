package com.kuaishou.raymond.algorithm.swordtooffer.day1_stack_and_queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class P9CQueue {

    private final Deque<Integer> stack1;

    private final Deque<Integer> stack2;

    public P9CQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            Integer value = stack1.pop();
            stack2.push(value);
        }

        return stack2.isEmpty() ?  -1 : stack2.pop();
    }

    public static void main(String[] args) {
        P9CQueue p9CQueue = new P9CQueue();
        p9CQueue.appendTail(3);
        p9CQueue.deleteHead();
        p9CQueue.deleteHead();
        p9CQueue.deleteHead();
    }

}
