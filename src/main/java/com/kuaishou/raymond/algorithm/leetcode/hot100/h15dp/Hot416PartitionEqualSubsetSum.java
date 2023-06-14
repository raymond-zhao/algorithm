package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 17:20
 * 题目：<a href="https://leetcode.cn/problems/partition-equal-subset-sum/?envType=study-plan-v2&id=top-100-liked">416. 分割等和子集</a>
 * 本题的难点在于对于题目的理解与转化，与 0-1 背包问题联系起来。
 */
public class Hot416PartitionEqualSubsetSum {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,5,11,5]");
        System.out.println("canPartition(nums) = " + canPartition(nums));
    }

    /**
     * 原始解法
     * 时间复杂度：O(NC)
     * 空间复杂度：O(NC)
     * 其中，C 为数组全部元素和的一半。
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        int maxNum = nums[0];
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 == 1) {
            return false;
        }
        if (maxNum > sum / 2) {
            return false;
        }
        if (maxNum == sum / 2) {
            return true;
        }

        int target = sum / 2;
        int len = nums.length;
        // 状态定义：dp[i][j] 表示前 0..i-1 个数字能否组成数字 j
        boolean[][] dp = new boolean[len][target + 1];

        // 基准条件：
        dp[0][0] = true;
        if (nums[0] == target) {
            dp[0][target] = true;
        }

        // 状态转移
        for (int i = 1; i < len; i++) {
            // 外层循环固定可选数字
            for (int j = 0; j <= target; j++) {
                // 照抄上一行的结果，前 i 个数字能否组成 j 取决于前 i-1 个数字能否组成 j（等价于如果不选第 i 个元素，则结果取决于上一行。）
                dp[i][j] = dp[i - 1][j];
                // 内层循环使用目前可选的所有数字，判断是否可相加成 j。
                // 每个数字，有「选」与「不选」两种选择
                if (j >= nums[i]) {
                    // dp[i-1][j]: 不选择当前数字，结果取决于前 i-1 个数字能否组成 j
                    // dp[i-1][j-nums[i]]: 选择当前数字，结果取决于前 i-1 个数字能否组成 j-nums[i]。
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    public boolean canPartitionCompressed(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        // 特例特判
        if ((sum & 1) == 1) {
            // 如果总和为奇数，则一定无法拆分成两个等和子集。
            return false;
        }
        if (max > sum / 2) {
            // 如果数组中最大的那个数字已经超过了总和的一半，则即使把这个数字单独放到一个子集，其他元素之和也没办法与它相等。
            return false;
        }

        int target = sum / 2;
        // dp[i]: 使用数组中目前可以选择的所有元素，是否可以相加组合成 i。
        boolean[] dp = new boolean[target + 1];

        // 初始状态：不选择任何元素，组成 0，可以。
        dp[0] = true;

        // 如果选择了第 0 个元素，则只能组成和为 nums[0] 的值。
        if (nums[0] == target) {
            dp[nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            // 需要倒序遍历
            for (int j = target; j >= nums[i]; j--) {
                if (dp[target]) {
                    // 如果已经组成了 target，提前返回。
                    return true;
                }
                // 要么 0..i-1 已经组成了 j 并且不选择 nums[i]，要么组成了 j-nums[i] 并且选择了 nums[i]。
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

}
