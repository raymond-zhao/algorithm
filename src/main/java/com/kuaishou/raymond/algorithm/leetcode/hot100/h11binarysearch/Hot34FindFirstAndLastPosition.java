package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 10:52
 * 题目：<a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/?envType=study-plan-v2&id=top-100-liked">34. 在排序数组中查找元素的第一个和最后一个位置</a>
 */
public class Hot34FindFirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {
        int leftRange = searchLeft(nums, target);
        if (leftRange == -1) {
            return new int[]{-1, -1};
        }
        int rightRange = searchRight(nums, target);
        return new int[]{leftRange, rightRange};
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                // 我们要寻找数字 target 第一次出现的位置，但是此时并不能确定 middle 是不是左区间，
                // 比如 ...8, 8, 8(middle),... 需要继续缩小右区间向左寻找看看。
                right = middle - 1;
            } else if (nums[middle] > target) {
                // target 比 nums[middle] 小，在 middle 的左边，middle 一定取不到，所以需要缩小右区间。
                right = middle - 1;
            } else {
                // target 比 nums[middle] 大，在 middle 的右边，(-\infty, middle] 一定小于 target，middle 一定取不到。
                // 而我们要寻找的是第一个大于等于 target 的值，所以需要扩大左区间。
                left = middle + 1;
            }
        }
        // 循环结束后，left=right+1，right指向小于 target 的最大数字。
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle =(left + right) >>> 1;
            if (nums[middle] == target) {
                // ...8,8(middle),8,...
                // left 指向大于 target 的最小数字
                left = middle + 1;
            } else if (nums[middle] < target) {
                // [left, middle] 均小于 target，而我们要寻找的是大于等于 target 的最大数字，middle 与其左侧的值一定取不到，所以需要在 [middle+1,right] 之间寻找。
                left = middle + 1;
            } else {
                // [middle, right] 均大于 target，而我们要寻找的是大于等于 target 的最大数字，middle 与其右侧的值一定取不到，所以需要在 [left, middle-1] 中继续寻找。
                right = middle - 1;
            }
        }
        if (right == -1 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}
