package com.kuaishou.raymond.algorithm.leetcode.hot100.h5array;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/product-of-array-except-self/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 238. 除自身以外数组的乘积
 */
public class Hot238ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,2,3,4]");
        AlgoUtils.printRow(productExceptSelf(nums));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        // 计算当前元素左侧所有元素的乘积
        int[] leftProduct = new int[len];
        leftProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        }
        // 计算当前元素右侧所有元素的乘积
        int[] rightProduct = new int[len];
        rightProduct[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = leftProduct[i] * rightProduct[i];
        }
        return nums;
    }

    public static int[] productExceptSelfII(int[] nums) {
        int[] data = new int[nums.length];
        data[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            data[i] = data[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            data[i] = data[i] * rightProduct;
            rightProduct *= nums[i];
        }
        return data;
    }
}
