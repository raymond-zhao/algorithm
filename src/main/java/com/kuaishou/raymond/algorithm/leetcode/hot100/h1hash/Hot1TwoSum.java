package com.kuaishou.raymond.algorithm.leetcode.hot100.h1hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/two-sum/submissions/">...</a>
 * - 哈希
 */
public class Hot1TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if (map.containsKey(difference)) {
                return new int[]{map.get(difference), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

}
