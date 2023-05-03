package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 08:53
 * 题目：<a href="https://leetcode.cn/problems/house-robber/?envType=study-plan-v2&id=top-100-liked">198. 打家劫舍</a>
 * - 相似题目：
 * - {@link com.kuaishou.raymond.algorithm.leetcode.all.P198HouseRobbing}
 * - 环形DP：<a href="https://leetcode.cn/problems/house-robber-ii/">213. 打家劫舍 II</a>
 * - 树形DP：<a href="https://leetcode.cn/problems/house-robber-iii/">337. 打家劫舍 III</a>
 * - 二分：<a href="https://leetcode.cn/problems/house-robber-iv/">2560. 打家劫舍 IV</a>
 */
public class Hot198HouseRobber {

    public int robCompressed(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // dp[i]：当房间 i 为止，最大偷窃价值。
        int nMinus2 = nums[0];
        int nMinus1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 每间房子有两种选择：偷与不偷。
            // 如果偷，则不能偷上一家，价值为 nums[i] + dp[i-2]
            // 如果不偷，则最大价值与到上一家时的收益相同。
            int current = Math.max(nums[i] + nMinus2, nMinus1);
            nMinus2 = nMinus1;
            nMinus1 = current;
        }
        return nMinus1;
    }

    /**
     * 原始 DP，无空间压缩。
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // dp[i]：当房间 i 为止，最大偷窃价值。
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 每间房子有两种选择：偷与不偷。
            // 如果偷，则不能偷上一家，价值为 nums[i] + dp[i-2]
            // 如果不偷，则最大价值与到上一家时的收益相同。
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

}
