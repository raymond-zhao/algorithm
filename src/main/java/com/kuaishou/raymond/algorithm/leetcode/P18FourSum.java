package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P18FourSum {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        int[] nums2 = {0, 0, 0, 0};
        int[] nums3 = {2, 2, 2, 2, 2};
        int target2 = 8;
        int[] nums4 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target4 = -294967296;
        System.out.println("fourSum(nums, target) = " + fourSum(nums, target));
        System.out.println("fourSum(nums2, target) = " + fourSum(nums2, target));
        System.out.println("fourSum(nums, target2) = " + fourSum(nums3, target2));
        System.out.println("fourSum(nums4, target4) = " + fourSum(nums4, target4));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> data = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return data;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        data.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return data;
    }

}
