package com.kuaishou.raymond.algorithm.leetcode.binary.search;

/**
 * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/">...</a>
 * 寻找旋转排序数组中的最小值，数组中包含重复值。
 * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/solution/gong-shui-san-xie-xiang-jie-wei-he-yuan-7xbty/">...</a>
 */
public class SearchRotatedSortedArrayP154 {

    public int findMin(int[] nums) {
        int data = nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == nums[right]) {
                data = Math.min(data, nums[middle]);
                right--;
            } else if (nums[middle] < nums[right]) {
                data = Math.min(data, nums[middle]);
                right = middle - 1;
            } else {
                data = Math.min(data ,nums[right]);
                left = middle + 1;
            }
        }
        return data;
    }

}
