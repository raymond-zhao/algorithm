package com.kuaishou.raymond.algorithm.leetcode.all;

/**
 * https://leetcode.cn/problems/remove-element/
 * 移除数组中值等于 val 的元素
 */
public class P27RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // slow 指向已访问过的元素中，不等于 val 的最后一个元素。
        int slow = -1;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[++slow] = nums[fast];
            }
        }

        return slow + 1;
    }
}
