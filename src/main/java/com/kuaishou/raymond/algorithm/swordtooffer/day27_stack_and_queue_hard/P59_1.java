package com.kuaishou.raymond.algorithm.swordtooffer.day27_stack_and_queue_hard;

import java.util.*;

public class P59_1 {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("Arrays.toString(maxSlidingWindowV3(nums, 3)).toString() = " + Arrays.toString(maxSlidingWindowV3(nums, 3)));
        System.out.println("Arrays.toString(maxSlidingWindow(nums, 3)) = " + Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    /**
     * lambda 暴力解
     */
    public int[] maxSlidingWindowV1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int len = nums.length - k + 1;
        int[] data = new int[len];
        int index = 0;

        for (int i = 0; i < len; i++) {
            OptionalInt max = Arrays.stream(nums, i, i + k).max();
            if (max.isPresent()) {
                data[index++] = max.getAsInt();
            }
        }
        return data;
    }

    public int[] maxSlidingWindowV2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int len = nums.length - k + 1;
        int[] data = new int[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            int currentMax = nums[i];
            for (int j = i; j < i + k; j++) {
                currentMax = Math.max(currentMax, nums[j]);
            }
            data[index++] = currentMax;
        }
        return data;
    }

    /**
     * 核心思想：遍历原数组，在 O(1) 时间内获取最大元素，使用 O(lgk) 时间调整大根堆。
     * 时间复杂度：O((n-k)lgk)
     * 空间复杂度：O(lgk)
     * 超时！！
     */
    public static int[] maxSlidingWindowV3(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        // 前 k 个元素形成的窗口
        for (int i = 0; i < k; i++) {
            // 3, 1, -1
            queue.offer(nums[i]);
        }

        int len = nums.length - k + 1;
        int[] data = new int[len];
        for (int i = k; i < nums.length; i++) {
            // [k+1, n]
            data[i - k] = queue.element();
            queue.remove(nums[i - k]);
            queue.offer(nums[i]);
        }

        // 特例处理
        queue.offer(nums[nums.length - 1]);
        data[len - 1] = queue.element();

        return data;
    }

    /**
     * 单调队列
     * 1. deque 内仅包含窗口内的元素，每轮窗口滑动移除了元素 nums[i-1]，需将 deque 内对应的元素一起删除。
     * 2. deque 内的元素**非严格递减** => 每轮窗口滑动添加了元素 nums[j+1]，需将 deque 内所有小于 nums[j+1] 的元素删除。
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] data = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            // 未形成窗口前
            while (!deque.isEmpty() && deque.peekLast() <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }

        data[0] = deque.getFirst();
        for (int i = k; i < nums.length; i++) {
            // 形成窗口后
            if (deque.getFirst() == nums[i - k]) {
                // 如果双端队列中的最大值是数组中即将滑出窗口的元素，将其移除队列。
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.getLast() <= nums[i]) {
                // 保持单调性，删除队列内所有小于即将入队的元素的元素。
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            data[i - k + 1] = deque.getFirst();
        }

        return data;
    }

    public static <E> E nullToDefault(E e, E e2) {
        return Optional.ofNullable(e).orElse(e2);
    }

}
