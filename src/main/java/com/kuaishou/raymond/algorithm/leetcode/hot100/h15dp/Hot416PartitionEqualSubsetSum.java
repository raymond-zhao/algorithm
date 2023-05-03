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
        // dp[i][j]：在数组 0..i 选择若干个元素相加，其和是否能凑成 j。
        boolean[][] dp = new boolean[len][target + 1];

        // 初始状态
        // 只要不选择第 0 个元素，和就为 0。在本题中，dp[0][0] 并不影响最终答案。
        dp[0][0] = true;
        // 在只选中第 0 个元素时，只有 dp[0][target] 这种情况能为 true。
        // dp[0][0..target-1] 与 dp[0][target+1..] 均不能等分。
        if (nums[0] == target) {
            dp[0][target] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 如果 0..i-1 之间的数字相加能凑成 j，那 0..i 之间的数字相加也一定能凑成 j，只要不选择 nums[i] 就行。
                dp[i][j] = dp[i - 1][j];

//                if (nums[i] == j) {
//                    // 如果当前数字可以直接凑成 j，甚至不用与 0..i-1 之间的数字组合，那肯定可以凑成 j 了。
//                    dp[i][j] = true;
//                    continue;
//                }
//
//                if (nums[i] < j) {
//                    // 要么 0..i-1 之间的数字已经凑成了 j，要么凑成了 j-nums[i]，
//                    // 如果是后者，我们只需要选择上 nums[i] 就可以凑成 j。
//                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
//                }

                // 如果初始化成 dp[0][0] = true，这里可以这么写。
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }

                // if (nums[i] > j)，也就是说某个单独的数字已经超过了数组总和的一半，
                // 肯定是 false，但是 Java 内 boolean 数组默认值就是 false，不设置也罢。
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
