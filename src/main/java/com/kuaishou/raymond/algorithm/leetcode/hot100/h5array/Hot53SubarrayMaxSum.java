package com.kuaishou.raymond.algorithm.leetcode.hot100.h5array;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/maximum-subarray/?envType=study-plan-v2&id=top-100-liked">53. 最大子数组和</a>
 * - 动态规划
 */
public class Hot53SubarrayMaxSum {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[-2,1,-3,4,-1,2,1,-5,4]");
        System.out.println("maxSubArray(nums) = " + maxSubArray(nums));
    }

    public static int maxSubArrayDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int data = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            data = Math.max(dp[i], data);
        }
        return data;
    }

    public static int maxSubArray(int[] nums) {
        int previous = nums[0];
        int data = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = Math.max(previous + nums[i], nums[i]);
            data = Math.max(current, data);
            previous = current;
        }
        return data;
    }

}
