package com.kuaishou.raymond.algorithm.leetcode.hot100.h13heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 22:49
 * 题目：<a href="https://leetcode.cn/problems/find-median-from-data-stream/?envType=study-plan-v2&id=top-100-liked">295. 数据流的中位数</a>
 */
public class Hot295FIndMedianOfDataStream {

    /**
     * 小根堆存放后 k/2 个元素，堆顶元素为后半段数据的起始位置。
     */
    private final PriorityQueue<Integer> minHeap;

    /**
     * 大根堆存放前 k/2 个元素，堆顶元素为前半段数据的结束位置。
     */
    private final PriorityQueue<Integer> maxHeap;

    public Hot295FIndMedianOfDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * 当数据个数为奇数时，取大根堆的堆顶元素作为中位数。
     * 当数据个数为偶数时，取大根堆与小根堆的堆顶元素之和的平均值作为中位数。
     * 也就是说，我们需要保证大根堆元素的个数不小于小根堆元素的个数。
     */
    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.element() + maxHeap.element()) / 2.0;
        }
        return minHeap.size() < maxHeap.size() ? maxHeap.element() : minHeap.element();
    }

}
