package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 * 移除排序数组中的重复项
 */
public class P26RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println("removeDuplicates(nums) = " + removeDuplicates(nums));
    }

    /**
     * 快慢指针
     * 慢指针：指向当前数组中不重复的元素
     */
    public static int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static int removeDuplicatesV2(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
