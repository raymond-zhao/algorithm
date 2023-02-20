package com.kuaishou.raymond.algorithm.swordtooffer.day27_stack_and_queue_hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-20 13:48
 */
public class MaxQueue {

    /**
     * 真正地存储数据的队列
     */
    private Queue<Integer> queue;

    /**
     * 单调递减队列
     */
    private Deque<Integer> deque;

    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if (deque.isEmpty()) {
            return -1;
        }
        return deque.peekFirst();
    }

    /**
     * 添加元素时维持双端队列的单调性
     */
    public void push_back(int value) {
        while (!deque.isEmpty() && deque.peekLast() < value) {
            // 移除双端队列中比待入队元素小的所有元素
            deque.pollLast();
        }
        queue.offer(value);
        deque.offerLast(value);
    }

    /**
     * 如果出队的元素等于单调队列中的最大值，同步移除单调队列中的该元素。
     */
    public int pop_front() {
        if (deque.isEmpty()) {
            return -1;
        }
        int data = queue.remove();
        if (data == deque.getFirst()) {
            deque.pollFirst();
        }
        return data;
    }
}
