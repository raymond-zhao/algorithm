package com.kuaishou.raymond.algorithm.leetcode.binary.search;

/**
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/">...</a>
 * 搜索旋转有序数组: 数组中的元素互不相同
 */
public class SearchRotatedSortedArrayP33 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int idx = search(nums, 0);
        System.out.println("idx = " + idx);
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                return middle;
            }
            // 执行至此，middle 一定不等于 target 了。
            // 判断 nums[middle..right] 是否为有序数组，如果是，则在其中搜索，如果不是，则在左子数组搜索。
            if (nums[middle] < nums[right]) {
                // 如果右子数组有序
                if (nums[middle] < target && target <= nums[right]) {
                    // 继续在右子数组中搜索
                    left = middle + 1;
                } else {
                    // 到左子树中搜索
                    right = middle - 1;
                }
            } else {
                // 如果左子数组 nums[left, middle] 有序
                if (nums[left] <= target && target < nums[middle]) {
                    // 继续在左子数组中搜索
                    right = middle - 1;
                } else {
                    // 到右子数组中搜索
                    left = middle + 1;
                }
            }
        }
        return -1;
    }
}
