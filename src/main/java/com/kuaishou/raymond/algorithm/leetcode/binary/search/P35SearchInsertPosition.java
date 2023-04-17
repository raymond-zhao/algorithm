package com.kuaishou.raymond.algorithm.leetcode.binary.search;

public class P35SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    public static int searchInsertV2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                right = middle - 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return left;
    }
}
