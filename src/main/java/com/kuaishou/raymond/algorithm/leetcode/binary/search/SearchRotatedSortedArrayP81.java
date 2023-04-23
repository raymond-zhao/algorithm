package com.kuaishou.raymond.algorithm.leetcode.binary.search;

/**
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/submissions/">...</a>
 * 搜索旋转有序数组: 数组中的元素不必互不相同
 */
public class SearchRotatedSortedArrayP81 {

    public static boolean search(int[] nums, int target) {
        return searchIndex(nums, target) == -1;
    }

    private static int searchIndex(int[] nums, int target) {
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
            if (nums[left] == nums[middle]) { // 改动点2
                ++left;
                continue;
            }
            // 执行至此，middle 一定不等于 target 了。
            // 判断 nums[middle..right] 是否为有序数组，如果是，则在其中搜索，如果不是，则在左子数组搜索。
            if (nums[middle] <= nums[right]) { // 改动点 1
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
