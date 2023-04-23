package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/next-permutation/">...</a>
 * 31. 下一个排列
 */
public class P31NextPermutation {

    public static void main(String[] args) {
        int[] nums = {7, 4, 5, 6, 3, 2, 1};
        nextPermutation(nums);
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = nums.length - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            // 找到第一个 nums[left] < nums[left+1] 的升序对
            --left;
        }
        // 在执行完上面的循环之后，nums[left+1...nums.length-1] 是递减的。
        if (left >= 0) {
            int right = nums.length - 1;
            // 因为 nums[left+1...nums.length-1] 是递减的，所以下面这个循环找到的第一个大于 nums[left] 的数，也是最小的大于 nums[left] 的数。
            while (right >= 0 && nums[right] <= nums[left]) {
                // 从右向左寻找第一个满足 nums[right] > nums[left] 的数字
                --right;
            }
            // 交换 left 与 right
            AlgoUtils.swap(nums, left, right);
        }
        // 反转 left+1 之后的所有数字
        // 因为 nums[left+1...nums.length-1] 是递减的，如 5, 3, 2, 1，但这并不是最小的排列，
        // 最小的排列应该是是递增的，所以需要翻转，变为 1, 2, 3, 5。
        reverse(nums, left + 1);
    }

    private static void reverse(int[] nums, int startIdx) {
        int left = startIdx;
        int right = nums.length - 1;
        while (left < right) {
            AlgoUtils.swap(nums, left++, right--);
        }
    }

}
