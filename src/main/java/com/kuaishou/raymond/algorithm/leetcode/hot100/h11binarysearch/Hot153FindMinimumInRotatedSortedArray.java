package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 12:55
 * 题目：<a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/?envType=study-plan-v2&id=top-100-liked">153. 寻找旋转排序数组中的最小值</a>
 */
public class Hot153FindMinimumInRotatedSortedArray {

    /**
     * [3,4,5,1,2]
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] > nums[right]) {
                // 中间值大于右端点，右半段区间呈现 V 字型先增后减，最小值一定在右区间，极小值为 nums[right]。
                left = middle + 1;
                min = Math.min(min, nums[right]);
            } else {
                // 中间值小于右端点，右半段区间递增，极小值为 nums[middle]，
                // 极小值不一定是最小值，向右寻找只会越找越大，所以向左区间寻找。
                min = Math.min(min, nums[middle]);
                right = middle - 1;
            }
        }
        return min;
    }
}
