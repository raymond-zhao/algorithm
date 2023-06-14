package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/6/6 13:29
 * 题目：<a href="https://leetcode.cn/problems/find-peak-element/">162. 寻找峰值</a>
 */
public class Hot162FindPeakElement {

    /**
     * 二分爬坡
     */
    public int findPeakElementBinarySearch(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] > nums[middle + 1]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 寻找最大值
     */
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }

}
