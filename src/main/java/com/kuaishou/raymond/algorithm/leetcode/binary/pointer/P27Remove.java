package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

public class P27Remove {

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int target = 3;
        removeElement(nums, target);
    }

    /**
     * 改变了数组的相对顺序，但是减少了很多不必要的交换。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // nums[0, left) 指向不等于 val 的值
        // nums[left] 可能等于 val，也可能不等于。
        // 如果等于，则以 nums[right--] 重新赋值，
        // 如果不等于，则向右移动。
        int left = 0;
        // right 只关心向左收缩并将值赋给 left，不关心是不是等于 val，
        // 如果 nums[right]=val，则赋值后 nums[left] 也等于 val，但是 left 不会向右移动，所以 left 始终指向等于 val 的位置。
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right--];
            } else {
                left++;
            }
        }
        return left;
    }

    public static int removeElementV2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // slow 始终指向不等于 val 的最后一个值
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
