package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import java.util.Arrays;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 14:48
 * 题目：
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/?envType=study-plan-v2&id=top-100-liked">300. 最长递增子序列</a>
 * 相似题目：<a href="https://leetcode.cn/problems/longest-common-subsequence/">1143. 最长公共子序列</a>
 */
public class Hor300LongestIncreasingSubsequences {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 41, 6, 62, 2, 5, 1, 2};
        System.out.println("lengthOfLIS(nums) = " + lengthOfLIS(nums));
    }

    /**
     * 求最长上升子序列的长度
     */
    public static int lengthOfLIS(int[] nums) {
        // dp[i]：到 nums[i] 为止的最长上升子序列的长度
        int maxLength = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            // 枚举 j..i 之间的所有数字
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp));
        return maxLength;
    }

}
