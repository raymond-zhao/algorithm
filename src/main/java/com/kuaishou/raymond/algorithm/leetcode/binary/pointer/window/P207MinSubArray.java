package com.kuaishou.raymond.algorithm.leetcode.binary.pointer.window;

/**
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">...</a>
 */
public class P207MinSubArray {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        int leftIdx = 0;
        int sum = 0;
        for (int rightIdx = 0; rightIdx < nums.length; rightIdx++) {
            sum += nums[rightIdx];
            while (sum >= target) {
                minLength = Math.min(minLength, rightIdx - leftIdx + 1);
                sum -= nums[leftIdx++];
            }
        }
        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }

}
