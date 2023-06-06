package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 10:34
 * 题目：二分搜索及其常见变种
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 2, 3, 4, 5, 6, 7, 7, 7, 78, 100};
        System.out.println("binarySearch(nums1, 7) = " + binarySearch(nums1, 7));
        System.out.println("binarySearchFirstAppeared(nums1, 7) = " + binarySearchFirstAppeared(nums1, 7));
        System.out.println("binarySearchLastAppeared(nums1, 7) = " + binarySearchLastAppeared(nums1, 7));
        System.out.println("binarySearchFirstGT(nums1,  7) = " + binarySearchFirstGT(nums1, 89));
        System.out.println("binarySearchLastGT(nums1, 77) = " + binarySearchLastGT(nums1, 77));
    }

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
     * 寻找第一个大于等于 target 的位置
     *
     * 寻找最后一个小于等于 target 的位置
     * 寻找最后一个大于等于 target 的位置
     */
    public static int binarySearchFirstGT(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] >= target) {
                result = middle;
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return result;
    }

    /**
     * 寻找最后一个不大于 target 的值的索引
     */
    public static int binarySearchLastGT(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1; // 默认值表示未找到

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (nums[mid] <= target) {
                result = mid; // 更新结果为当前不大于目标值的索引
                left = mid + 1; // 继续在右半边搜索
            } else {
                right = mid - 1; // 在左半边搜索
            }
        }

        return result;
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
                // 搜素到与 target 相等的值，继续向左搜索。
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
                // 搜索到与 target 相等的值，继续向右搜索。
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
