package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 15:28
 * 题目：<a href="https://leetcode.cn/problems/maximum-product-subarray/?envType=study-plan-v2&id=top-100-liked">152. 乘积最大子数组</a>
 * - 相似题目：
 * <a href="https://leetcode.cn/problems/maximum-subarray/">53. 最大子数组和</a>
 */
public class Hot152MaximumProductSubarray {

    /**
     * 本题出现了两种状态，要么使用两个一维数组表示，要么使用一个二维数组表示.
     * 原始动态规划，无空间压缩。
     */
    public int maxProduct(int[] nums) {
        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];
        maxDP[0] = nums[0];
        minDP[0] = nums[0];
        int maxProduct = maxDP[0];
        for (int i = 1; i < nums.length; i++) {
            maxDP[i] = Math.max(nums[i], Math.max(nums[i] * maxDP[i - 1], nums[i] * minDP[i - 1]));
            minDP[i] = Math.min(nums[i], Math.min(nums[i] * minDP[i - 1], nums[i] * maxDP[i - 1]));
            maxProduct = Math.max(maxProduct, maxDP[i]);
        }
        return maxProduct;
    }

    public int maxProductCompressed(int[] nums) {
        int maxDP = nums[0];
        int minDP = nums[0];
        int maxProduct = maxDP;
        for (int i = 1; i < nums.length; i++) {
            int mx = maxDP;
            int mn = minDP;
            maxDP = Math.max(nums[i], Math.max(nums[i] * mx, nums[i] * mn));
            minDP = Math.min(nums[i], Math.min(nums[i] * mn, nums[i] * mx));
            maxProduct = Math.max(maxProduct, maxDP);
        }
        return maxProduct;
    }

}
