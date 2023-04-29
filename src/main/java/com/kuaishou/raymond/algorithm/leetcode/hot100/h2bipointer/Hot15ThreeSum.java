package com.kuaishou.raymond.algorithm.leetcode.hot100.h2bipointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-27 21:06
 * <a href="https://leetcode.cn/problems/3sum/">...</a>
 * 15. 三数之和
 */
public class Hot15ThreeSum {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toArray("[-1,0,1,2,-1,-4]");
        System.out.println("threeSum(nums) = " + threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    data.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return data;
    }
}
