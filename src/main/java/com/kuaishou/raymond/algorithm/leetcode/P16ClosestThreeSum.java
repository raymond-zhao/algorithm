package com.kuaishou.raymond.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/3sum-closest/
 */
public class P16ClosestThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int[] nums2 = {-100, -98, -2, -1};
        int target = 1;
        int target2 = -101;
        System.out.println("threeSumClosest(nums, target) = " + threeSumClosest(nums, target));
        System.out.println("threeSumClosest(nums, target2) = " + threeSumClosest(nums2, target2));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int left = 0; left < nums.length - 2; left++) {
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }
            int middle = left + 1;
            int right = nums.length - 1;
            while (middle < right) {
                int sum = nums[left] + nums[middle] + nums[right];
                if (target == sum) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                } else if (sum < target) {
                    while (middle < right && nums[middle] == nums[middle + 1]) {
                        middle++;
                    }
                    middle++;
                } else {
                    while (middle < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                }
            }
        }
        return res;
    }

}
