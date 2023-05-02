package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 12:02
 * 题目：<a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/?envType=study-plan-v2&id=top-100-liked">33. 搜索旋转排序数组</a>
 */
public class Hot33SearchInRotatedSortedArray {

    /**
     * 以 [4,5,6,7,0,1,2]，target=0 为例，
     * 有序数组在旋转之后包含两个有序区间，分别是 [4,5,6,7] 与 [0,1,2]，
     * 使用普通的二分搜索的话，middle=(0+6)/2=3，
     * - 而nums[3]=7>target=0，设置right=middle-1，
     * - 如果 target=7=nums[3]，则可以提前返回。
     * ----- 下面讨论的都是 nums[middle] != target 的情况，也就是说 middle 这个索引取不到。
     * 这样在 [left,middle-1]中肯定是搜索不到结果的，或者说结果是错误的。
     * 为了保证 nums[middle] 与 target 比较的正确性，我们需要先保证搜索区间的有序性，
     * 所以，我们首先需要确定要搜索的单调区间，那么应该如何确定单调区间呢？
     * 我们可以使用 nums[middle] 与 nums[right] 作比较
     * - 如果 nums[middle] < nums[right]，说明右区间 (middle, right] 是有序的，
     * - 如果 nums[middle] > nums[right]，说明左区间 [left, middle) 是有序的。
     * 在确定了有序区间之后，我们需要进一步确定 target 是否在这个有序区间之内，
     * 在左区间 [left,middle) 中的判断方式为：nums[left] <= target && target < nums[middle]
     * 在右区间 (middle,right] 中的判断方式为：nums[middle] < target && target <= nums[right]
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                // 如果直接找到，皆大欢喜，提前返回。
                return middle;
            }
            // 如果 nums[middle] != target，也就是说 middle 这个点一定没用，则开始确定有序区间的位置。
            if (nums[middle] < nums[right]) {
                // 如果右区间有序
                if (nums[middle] < target && target <= nums[right]) {
                    // 如果 target 在右区间之内，则只在右区间中寻找。
                    left = middle + 1;
                } else {
                    // 如果 target 不在右区间这个有序区间内，到左区间这个无序区间中继续寻找。
                    right = middle - 1;
                }
            } else {
                if (nums[left] <= target && target < nums[middle]) {
                    // 如果 target 在左区间这个有序区间内，则缩小右边界。
                    right = middle - 1;
                } else {
                    // 如果 target 不在左区间这个有序区间内，到右区间这个无序区间内继续搜索。
                    left = middle + 1;
                }
            }
        }
        // 如果 target 存在，则在循环中已经搜索到并返回，流程执行到这里，说明没有找到 target。
        return -1;
    }
}
