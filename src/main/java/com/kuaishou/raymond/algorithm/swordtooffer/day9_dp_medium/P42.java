package com.kuaishou.raymond.algorithm.swordtooffer.day9_dp_medium;

/**
 * 连续子数组的最大和
 * 输入一个整形数组，数组中的一个或多个整数组成一个子数组，求所有子数组和的最大值。
 * 要求时间复杂为 O(n).
 */
public class P42 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // 6
        P42 p42 = new P42();
        System.out.println("p42.maxSubArray(nums) = " + p42.maxSubArray(nums));
        System.out.println("p42.maxSubArray1(nums) = " + p42.maxSubArray1(nums));
        System.out.println("p42.maxSubArray3(nums) = " + p42.maxSubArray3(nums));
    }

    public int maxSubArray1(int[] nums) {
        int maxSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k < j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    /**
     * dp[i] 表示到第 i 个数字形成的子数组为止，子数组和的最大值。
     * dp 数组：[-2, 1, -2, 4, 3, 5, 6, 1, 5]
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int maxSum = 0;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            maxSum = Math.max(dp[i], maxSum);
        }
        return maxSum;
    }

    /**
     * 在原数组上修改
     */
    public int maxSubArray3(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] = nums[i - 1] + nums[i];
            }
            // nums[i] += Math.max(0, nums[i - 1]);
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
