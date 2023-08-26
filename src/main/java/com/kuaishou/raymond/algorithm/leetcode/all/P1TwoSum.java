package com.kuaishou.raymond.algorithm.leetcode.all;

import java.lang.reflect.Method;
import java.util.Arrays;
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
        System.out.println("numOfFourSumCombination(A, B, C, D) = " + numOfFourSumCombination(A, B, C, D));
        System.out.println("Arrays.toString(threeSum(nums, 18)) = " + Arrays.toString(threeSum(nums, 18)));
        System.out.println("Arrays.toString(fourSum(nums, 29)) = " + Arrays.toString(fourSum(nums, 29)));

        Method[] declaredMethods = P1TwoSum.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("method.getName() = " + method.getName());
        }
    }

    /**
     * 寻找数组中和等于 target 的两个元素
     */
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
     * 寻找数组中和等于 target 的三个元素
     * T=O(n^2)
     * S=O(1)
     */
    private static int[] threeSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 3) {
            return new int[0];
        }
        Arrays.sort(nums); // O(n\lgn)
        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            int left = i + 1;
            int right = nums.length - 1;
            int currentNum = nums[i];
            int twoSum = target - currentNum;
            while (left < right) {
                int tempSum = nums[left] + nums[right];
                if (tempSum == twoSum) {
                    return new int[] {currentNum, nums[left], nums[right]};
                } else if (tempSum < twoSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new int[0];
    }

    /**
     * 寻找数组中和等于 target 的四个元素
     */
    private static int[] fourSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 4) {
            return new int[0];
        }

        Arrays.sort(nums); // O(nlgn)
        for (int i = 0; i < nums.length - 3; i++) {
            int numI = nums[i];
            for (int j = i + 1; j < nums.length -2; j++) {
                int numJ = nums[j];
                int twoSum = numI + numJ;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sumTwo = nums[left] + nums[right];
                    if (sumTwo + twoSum == target) {
                        return new int[] {numI, numJ, nums[left], nums[right]};
                    } else if (sumTwo + twoSum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return new int[0];
    }

    /**
     * A,B,C,D 四个数组，找组合数(i,j,k,l)，使得A[i] + B[j] + C[k] + D[l] = 0，求有多少种组合，要求时间复杂度n^2
     */
    public static int numOfFourSumCombination(int[] A, int[] B, int[] C, int[] D) {
        return numOfFourSumCombination(A, B, C, D, 0);
    }

    /**
     * 以 O(n^2) 的时间复杂度查找 A，B，C，D 四个数组中各取一个元素相加之和等于 target 的组合数。
     */
    public static int numOfFourSumCombination(int[] A, int[] B, int[] C, int[] D, int target) {
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
