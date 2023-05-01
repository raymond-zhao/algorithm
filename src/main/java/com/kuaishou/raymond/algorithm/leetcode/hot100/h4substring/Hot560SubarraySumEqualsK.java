package com.kuaishou.raymond.algorithm.leetcode.hot100.h4substring;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 560. 和为 K 的子数组：只关心个数，不关心具体的解。
 * 暴力解法
 * 前缀和
 * 前缀和扩展题：
 * <a href="https://leetcode.cn/problems/4sum-ii/">...</a>
 * <a href="https://leetcode.cn/problems/count-number-of-nice-subarrays/">...</a>
 */
public class Hot560SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,1,1]");
        int k = 2;
        // System.out.println("subarraySum(nums, k) = " + subarraySumBrutalForce(nums, k));
        System.out.println("subarraySum(nums, k) = " + subarraySum(nums, k));
    }

    /**
     * 前缀和
     * 核心思想：计算完当前前缀和之后，再去查查之前出现过多少次前缀和等于 prefixSum-k 的情况。
     * 前缀和数组中：prefixSum[j] + k = prefix[i]
     */
    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int counter = 0;
        Map<Integer/*前缀和*/, Integer/*前缀和出现的次数*/> map = new HashMap<>();
        map.put(0, 1);

        // 求前缀和
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                // 如果之前包含过这个前缀和，则
                counter += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return counter;
    }

    /**
     * 暴力搜索：固定一边，枚举另一边。
     * 时间复杂度：O(n^2)
     */
    public int subarraySumBrutalForceII(int[] nums, int k) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j >= 0; --j) {
                sum += nums[j];
                if (sum == k) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * 暴力法，会超时。
     * 时间复杂度：O(k * n^2)
     */
    public static int subarraySumBrutalForce(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int counter = 0;
        // 枚举所有的子串，计算其和。
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long sum = summation(nums, i, j);
                if (sum == k) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static long summation(int[] nums, int left, int right) {
        long sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }

}
