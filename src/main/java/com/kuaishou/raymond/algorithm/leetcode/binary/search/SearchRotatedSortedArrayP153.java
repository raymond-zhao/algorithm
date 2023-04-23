package com.kuaishou.raymond.algorithm.leetcode.binary.search;

/**
 * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/">...</a>
 * 153. 寻找旋转排序数组中的最小值，数组中不包含重复元素。
 */
public class SearchRotatedSortedArrayP153 {

    public int findMin(int[] nums) {
        int data = nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] < nums[right]) {
                // 如果 nums[middle...right] 是有序数组，最小值为 nums[middle]，
                data = Math.min(data, nums[middle]);
                // 但 nums[middle] 只是局部最小值，不一定是全局最小值，需要继续向左寻找。
                right = middle - 1;
            } else {
                // nums[middle] >= nums[right]，最小值一定在 middle 右侧。
                data = Math.min(data, nums[right]);
                left = middle + 1;
            }
        }
        return data;
    }

}
