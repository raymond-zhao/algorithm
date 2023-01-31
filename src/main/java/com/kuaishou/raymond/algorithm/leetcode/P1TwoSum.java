package com.kuaishou.raymond.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-01-31 16:53
 */
public class P1TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 8, 8, 6, 7};
        int[] res = twoSum(nums, 8);
        System.out.println("Arrays.toString(res) = " + Arrays.toString(res));
    }

    private static int[] twoSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);

        for (int idx = 1; idx < nums.length; idx++) {
            int residual = target - nums[idx];
            if (map.containsKey(residual)) {
                return new int[]{map.get(residual), idx};
            }
            map.put(nums[idx], idx);
        }
        return new int[0];
    }

}
