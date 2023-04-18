package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

/**
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
public class P977SortArraySquares {

    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] data = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int idx = nums.length - 1;
        while (left <= right) {
            int leftAbsValue = Math.abs(nums[left]);
            int rightAbsValue = Math.abs(nums[right]);
            if (leftAbsValue <= rightAbsValue) {
                data[idx--] = rightAbsValue * rightAbsValue;
                right--;
            } else {
                data[idx--] = leftAbsValue * leftAbsValue;
                left++;
            }
        }
        return data;
    }

}
