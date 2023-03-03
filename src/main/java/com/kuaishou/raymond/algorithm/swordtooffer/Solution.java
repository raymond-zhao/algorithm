package com.kuaishou.raymond.algorithm.swordtooffer;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 1, 1, 0};
        System.out.println("findRepeatNumber(nums) = " + findRepeatNumber(nums));
    }

    public static int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int currentNum = nums[i];
            if (currentNum != i) {
                // 如果当前元素与其下标不等，则将其归位（使元素与下标相等）
                swap(nums, i, currentNum);
                continue;
            }
            if (currentNum != nums[currentNum]) {
                return currentNum;
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int num) {
        int temp = nums[i];
        nums[i] = nums[num];
        nums[num] = temp;
    }

}
