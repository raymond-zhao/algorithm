package com.kuaishou.raymond.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-24 11:14
 * <a href="https://leetcode.cn/problems/first-missing-positive/">...</a>
 * 给你一个未排序的整数数组 nums，请你找出其中没有出现的最小正整数。
 * 时间复杂度必须为 O(n)，空间复杂度必须为 O(1)。
 */
public class P41FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0}; // 3
        int[] nums2 = {3, 4, -1, 1}; // 2
        int[] nums3 = {7, 8, 9, 11, 12}; // 1
        System.out.println("firstMissingPositiveV2(nums2) = " + firstMissingPositiveV2(nums2));
        System.out.println("firstMissingPositiveV2(nums3) = " + firstMissingPositiveV2(nums3));
    }

    public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length - 1;
    }

    public static int firstMissingPositiveV2(int[] nums) {
        int n = nums.length;
        // 1. 将数组中的非整数数转为 N+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 2. 将 x \in [1, n] 之间的数转为负数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        // 3. 寻找第一个正数
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // 4. 如果所有的数都是正数，则缺少的是 n+1。
        return n + 1;
    }

    /**
     * 原地哈希，将 nums[i] 映射到 i-1 位置。
     */
    public int firstMissingPositiveV3(int[] nums) {
        int len = nums.length;
        // 手动实现简单哈希表，即将值 i 映射到 i - 1 为作为索引。
        for (int i = 0; i < len; i++) {
            // 保证正整数，保证 <=n。
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 如果数在指定范围内，并且没有映射到正确位置。
                // 进行交换
                swap(nums, nums[i] - 1, i);
            }
        }
        // 在执行完上面的操作后，可以保证符合条件的正数 i 都映射到了原数组中 i - 1 索引的桶
        // 再遍历一遍原数组，如果当前位置的值 != 索引+1，说明它就是那个缺失的位置
        // 但是索引是比实际要寻找的正数小 1 的，所以返回时要加上 1
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 如果在上面的循环中都没有返回出去，说明数组中存在的数字刚好是 1 ~ len，那应该返回的是长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
