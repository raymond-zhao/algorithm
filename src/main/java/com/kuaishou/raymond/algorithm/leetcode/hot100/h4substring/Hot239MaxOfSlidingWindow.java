package com.kuaishou.raymond.algorithm.leetcode.hot100.h4substring;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&id=top-100-liked">239. 滑动窗口最大值</a>
 * - 滑动窗口
 * - 大根堆/优先队列
 * - 单调队列
 */
public class Hot239MaxOfSlidingWindow {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,3,-1,-3,5,3,6,7]");
        int[] nums2 = {7, 6, 5, 4, 3};
        int k = 3;
        int[] data = maxSlidingWindowMaxHeapII(nums, k);
        AlgoUtils.printRow(data);
    }

    /**
     * 单调递减队列
     * 1. 针对前 k 个元素初始化单调队列，单调队列内按元素索引递增，值递减。
     * 2. 初始化结果集，将初始化的单调队列中的头元素加入结果集。
     * 3. 处理 [k, n-1] 元素，依然维持队列的单调性。
     * 4. 移除单调队列中已经滑出窗口左区间的元素索引。
     * 5. 添加结果集
     */
    public static int[] maxSlidingWindowMonoQueue(int[] nums, int k) {
        // 作为单调递减队列使用，队列中元素索引递增，元素索引对应的元素递减。
        Deque<Integer> monoQueue = new ArrayDeque<>();
        // 利用前 k 个元素构造初始的单调队列
        for (int i = 0; i < k; i++) {
            while (!monoQueue.isEmpty() && nums[monoQueue.peekLast()] <= nums[i]) {
                // 出队队列中所有小于等于 nums[i] 的元素，使队列中第 0 个元素到 nums[i] 按元素值递减。
                // 如果队列尾部元素小于等于要入队的元素，则出队。
                monoQueue.pollLast();
            }
            monoQueue.offerLast(i);
        }
        int n = nums.length;
        int[] data = new int[n - k + 1];
        data[0] = nums[monoQueue.getFirst()];

        for (int i = k; i < n; i++) {
            while (!monoQueue.isEmpty() && nums[monoQueue.peekLast()] <= nums[i]) {
                monoQueue.pollLast();
            }
            monoQueue.offerLast(i);
            // 移除已经滑出了窗口，但却为队列头部的元素。
            while (!monoQueue.isEmpty() && monoQueue.getFirst() <= i - k) {
                monoQueue.pollFirst();
            }
            data[i - k + 1] = nums[monoQueue.getFirst()];
        }
        return data;
    }

    public static int[] maxSlidingWindowMaxHeapII(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }
        int len = nums.length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int[] data = new int[len - k + 1];
        // 使用数组中的前 k 个元素，形成第一个滑动窗口。
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[] {nums[i], i});
        }
        // 此时已形成第一个窗口，将第一个窗口内的最大值加入结果集。
        data[0] = maxHeap.element()[0];

        for (int i = k; i < len; i++) {
            maxHeap.offer(new int[] {nums[i], i});
            while (maxHeap.element()[1] <= i - k) {
                // 最大值可能并不在当前滑动窗口中，大根堆堆顶元素可能出现在当前滑动窗口左边界的左侧。
                // 这种情况下，不断移除堆顶的元素，直到这个堆顶元素确确实实出现在滑动窗口中。
                maxHeap.poll();
            }
            data[i - k + 1] = maxHeap.element()[0];
        }

        return data;
    }

    /**
     * 大根堆：超时
     */
    public static int[] maxSlidingWindowMaxHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }
        int len = nums.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int[] data = new int[len - k + 1];
        // 使用数组中的前 k 个元素，形成第一个滑动窗口。
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        // 此时已形成第一个窗口，将第一个窗口内的最大值加入结果集。
        data[0] = maxHeap.element();
        for (int i = 1; i < len - k + 1; i++) {
            // 去除即将滑出窗口的最左侧元素
            maxHeap.remove(nums[i - 1]);
            // 添加即将进入窗口的最右侧元素
            maxHeap.offer(nums[i + k - 1]);
            data[i] = maxHeap.element();
        }
        return data;
    }

}
