package com.kuaishou.raymond.algorithm.leetcode.hot100.h13heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 20:29
 * 题目：<a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/?envType=study-plan-v2&id=top-100-liked">215. 数组中的第K个最大元素</a>
 * 要求：时间复杂度 O(n)
 */
public class Hot215KthLargestElement {

    /**
     * 使用快排中的 partition 寻找第 k 大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int middle = lomutoPartition(nums, left, right);
            if (middle == target) {
                return nums[middle];
            } else if (middle < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
    }

    /**
     * 采用 Lomuto 分区
     */
    private int lomutoPartition(int[] nums, int left, int right) {
        int idx = left - 1;
        int pivot = nums[right];
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                ++idx;
                swap(nums, idx, i);
            }
        }
        swap(nums, idx + 1, right);
        return idx + 1;
    }

    private int hoarePartition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int l = left;
        int r = right - 1;
        while (l < r) {
            while (l < r && nums[l] <= pivot) {
                ++l;
            }
            while (l < r && nums[r] > pivot) {
                --r;
            }
            swap(nums, l, r);
        }
        swap(nums, l, right);
        return l;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public int findKthLargestHeapII(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.element()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.element();
    }

    public int findKthLargestHeapI(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.element();
    }

    public int findKthLargestIII(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
