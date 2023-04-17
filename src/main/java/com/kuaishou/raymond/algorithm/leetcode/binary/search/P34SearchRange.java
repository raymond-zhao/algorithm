package com.kuaishou.raymond.algorithm.leetcode.binary.search;

import java.util.Arrays;

public class P34SearchRange {

    public static void main(String[] args) {
        P34SearchRange p34 = new P34SearchRange();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 15;
        int[] nums2 = {1};
        int target2 = 0;
        System.out.println("searchLeft(nums, 7) = " + searchRight(nums, 7));
        System.out.println("searchLeft(nums, 4) = " + searchRight(nums, 4));
        System.out.println("searchLeft(nums, 9) = " + searchRight(nums, 9));
        System.out.println("searchLeft(nums, 12) = " + searchRight(nums, 12));
        System.out.println("Arrays.toString(p34.searchRange(nums, target)) = " + Arrays.toString(p34.searchRange(nums, target)));
    }

    /**
     * 搜索数字 target 在 nums 中第一次出现的位置和最后一次出现的位置
     * 也可以理解成：搜索数字 target 的左区间和右区间
     * 对此，可以在 {@link P35SearchInsertPosition#searchInsert(int[], int)} 搜索插入位置的基础上（插入到最左边），进行两次二分。
     */
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums, target);
        if (left == -1) {
            return new int[] {-1, -1};
        }
        int right = searchRight(nums, target);
        return new int[]{left, right};
    }

    /**
     * 寻找数字 target 的插入位置
     * 寻找 >= target 的第一个值
     */
    public static int searchLeft(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] >= target) {
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
     * 寻找数字 target 的右边界，如果存在，则返回其索引，否则返回 -1。
     * @param nums 排序数组
     * @param target 目标值
     * @return target 右边界索引或 -1
     */
    public static int searchRight(int[] nums, int target) {
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
