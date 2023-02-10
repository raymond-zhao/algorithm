package com.kuaishou.raymond.algorithm.swordtooffer.day17_sort_medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * P41：数据流的中位数
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 如 [2, 3, 4] 中位数为 3，[2, 3] 中位数为 (2+3)/2=2.5.
 * 设计一个支持一下两种操作的数据结构：
 * - void addNum(int num): 从数据流中添加一个数字到数据结构中；
 * - double findMedian(): 返回目前所有元素的中位数。
 * ---------------------------------------------
 * 可以考虑使用两个堆实现，大根堆用于保存数据流中的前 n/2 个元素，小根堆用于保存数据流中的后 n/2 元素，
 * 这样，数据流重的数字个数为奇数个时，小根堆的堆顶元素即为中位数；
 * 数据流中的数字为偶数个时，则中位数为小根堆与大根堆堆顶元素和的一半。
 */
public class P41 {

    private final Queue<Integer> minHeap;

    private final Queue<Integer> maxHeap;

    public static void main(String[] args) {
        P41 p41 = new P41();
        p41.addNum(1);
        p41.addNum(2);
        System.out.println("p41.findMedian() = " + p41.findMedian()); // 1.50000
        p41.addNum(3);
        System.out.println("p41.findMedian() = " + p41.findMedian()); // 2.00000
    }

    /**
     * 当数据流中的数据个数为偶数个，即 minHeap.size == maxHeap.size 时，median=(minHeap.peek() + maxHeap.peek()) / 2.0;
     * 当数据流中的数据个数为奇数个，即 minHeap.size != maxHeap.size 时:
     * - 如果 minHeap.size = maxHeap.size + 1, median=minHeap.peek();
     * - 如果 minHeap.size = maxHeap.size - 1, median=maxHeap.peek();
     */
    public P41() {
        // 小根堆，保存较大的一半，堆顶元素为中位数或中位数之一。
        minHeap = new PriorityQueue<>(Comparator.naturalOrder());
        // 大根堆，保存较小的一半，堆顶元素为中位数或中位数之一。
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * 添加元素时，需要把当前元素放到正确的堆中，不能随便放进大根堆或者小根堆。
     */
    public void addNum(int num) {
        if (minHeap.size() != maxHeap.size()) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.element() + maxHeap.element()) / 2.0;
        }
        return minHeap.size() < maxHeap.size() ? maxHeap.element() : minHeap.element();
    }

}
