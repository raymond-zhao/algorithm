package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 10:34
 * 题目：二分搜索及其常见的基本变种
 */
public class BinarySearch {

    /**
     * 最简单的二分搜索，如果找到 target 则返回其索引，否则返回 -1。
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找插入位置：找到第一个大于等于 target 的值所在的位置
     */
    public static int binarySearchGE(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 寻找插入位置：找到最后一个小于等于 target 的值所在的位置
     */
    public static int binarySearchGLE(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    /**
     * 寻找插入位置：找到 target 第一次出现的位置（左区间）
     */
    public static int binarySearchFirstAppeared(int[] nums, int target) {
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
        // left 是一直向右的，如果最后越界了，或者未找到 target，返回 -1。
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 寻找插入位置：找到 target 最后一次出现的位置（右区间）
     */
    public static int binarySearchLastAppeared(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 当搜索区间不为空时
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                // 搜索右边界，如果已经查询到，则继续向右找找看。
                left = middle + 1;
            } else if (nums[middle] > target) {
                // 如果当前值处于 target 右侧，则大于 target，向左寻找看看。
                right = middle - 1;
            } else {
                // 如果当前值处于 target 左侧，则小于 target，向右寻找看看。
                left = middle + 1;
            }
        }
        if (right == -1 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}
