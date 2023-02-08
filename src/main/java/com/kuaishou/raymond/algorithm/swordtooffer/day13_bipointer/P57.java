package com.kuaishou.raymond.algorithm.swordtooffer.day13_bipointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字 s，在数组中查找两个数，使得它们的和正好是 s。如果有多对数字的和等于 s，则输出任意一对即可。
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * --------------------
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 */
public class P57 {

    public static void main(String[] args) {
    }

    public int[] twoSumBiPointer(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == target - nums[right]) {
                return new int[] {nums[left], nums[right]};
            } else if (nums[left] > target - nums[right]) {
                --right;
            } else {
                ++left;
            }
        }
        return new int[0];
    }

    public int[] twoSumBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            int index = Arrays.binarySearch(nums, difference);
            if (index > 0) {
                return new int[] {nums[i], nums[index]};
            }
        }
        return new int[0];
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if (map.containsKey(difference)) {
                return new int[] {nums[map.get(difference)], nums[i]};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
