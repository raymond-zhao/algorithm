package com.kuaishou.raymond.algorithm.leetcode.hot100.h5array;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 189. 轮转数组
 */
public class Hot189RotateArray {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toArray("[1,2]");
        int k = 3;
        rotate(nums, k);
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        // 全部翻转
        reverse(nums, 0, nums.length - 1);
        // 翻转 0..k-1
        reverse(nums, 0, k - 1);
        // 翻转 k..n-1
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 使用额外数组，不符合题意。
     */
    public void rotateII(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

}
