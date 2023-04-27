package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-18 17:45
 */
public class P15ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("threeSum(nums) = " + threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> data = new ArrayList<>();
        // 固定最左边的指针
        for (int left = 0; left < nums.length - 2; left++) {
            if (nums[left] > 0) {
                break;
            }
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }
            int middle = left + 1;
            int right = nums.length - 1;
            while (middle < right) {
                int sum = nums[left] + nums[middle] + nums[right];
                if (sum == 0) {
                    data.add(Arrays.asList(nums[left], nums[middle], nums[right]));
                    while (middle < right && nums[middle] == nums[middle + 1]) {
                        middle++;
                    }
                    while (middle < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    middle++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    middle++;
                }
            }
        }
        return data;
    }
}
