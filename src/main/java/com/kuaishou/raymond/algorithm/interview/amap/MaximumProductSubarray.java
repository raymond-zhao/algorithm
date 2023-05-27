package com.kuaishou.raymond.algorithm.interview.amap;

/**
 * Author: raymond
 * CreateTime: 2023/5/27 08:46
 * 题目：<a href="https://leetcode.cn/problems/maximum-product-subarray/">152. 乘积最大子数组</a>
 */
public class MaximumProductSubarray {

    /**
     * 获取 nums 中的乘积最大的子数组
     * @param nums input
     * @return output
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] maxProductDP = new int[len];
        int[] minProductDP = new int[len];
        int maxProduct = nums[0];
        maxProductDP[0] = nums[0];
        minProductDP[0] = nums[0];

        for (int i = 1; i < len; i++) {
            maxProductDP[i] = Math.max(nums[i], Math.max(maxProductDP[i - 1] * nums[i], minProductDP[i - 1] * nums[i]));
            minProductDP[i] = Math.min(nums[i], Math.min(maxProductDP[i - 1] * nums[i], minProductDP[i - 1] * nums[i]));
            maxProduct = Math.max(maxProduct, maxProductDP[i]);
        }
        return maxProduct;
    }

}
