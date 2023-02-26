package com.kuaishou.raymond.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-01-31 16:53
 * 1. 两数之和
 * 2. 所有的两数之和
 * 3. 三数之和
 * 4. 四数之和
 * 5. 四数相加
 */
public class P1TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 8, 8, 6, 7};
        int[] res = twoSum(nums, 8);
        int[] A = {2, -3, 4, 5};
        int[] B = {2, 3, -4, 5};
        int[] C = {2, 3, 4, -5};
        int[] D = {2, 3, 4, 5};
        // System.out.println("Arrays.toString(res) = " + Arrays.toString(res));
        System.out.println("numOfFourSumCombinumnation(A, B, C, D) = " + numOfFourSumCombinumnation(A, B, C, D));
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
                return new int[] {map.get(residual), idx};
            }
            map.put(nums[idx], idx);
        }
        return new int[0];
    }

    /**
     * A,B,C,D 四个数组，找组合数(i,j,k,l)，使得A[i] + B[j] + C[k] + D[l] = 0，求有多少种组合，要求时间复杂度n^2
     */
    public static int numOfFourSumCombinumnation(int[] A, int[] B, int[] C, int[] D) {
        return numOfFourSumCombinumnation(A, B, C, D, 0);
    }

    public static int numOfFourSumCombinumnation(int[] A, int[] B, int[] C, int[] D, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int combination = a + b;
                // 统计 A[i] + B[j] 的组合出现了多少次
                map.put(combination, map.getOrDefault(combination, 1) + 1);
            }
        }

        int count = 0;
        for (int c : C) {
            for (int d : D) {
                int combination = target - c - d;
                count += map.getOrDefault(combination, 0);
            }
        }
        return count;
    }

}
