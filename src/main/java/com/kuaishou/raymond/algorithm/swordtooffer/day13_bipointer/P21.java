package com.kuaishou.raymond.algorithm.swordtooffer.day13_bipointer;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 */
public class P21 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums2 = {1, 3, 5};
        P21 p21 = new P21();
        System.out.println("Arrays.toString(p21.exchange(nums)) = " + Arrays.toString(p21.exchange(nums)));
        System.out.println("Arrays.toString(p21.exchange(nums)) = " + Arrays.toString(p21.exchange(nums2)));
    }

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && isOdd(nums[left])) {
                ++left;
            }
            while (left < right && isEven(nums[right])) {
                --right;
            }
            swap(nums, left, right);
        }
        return nums;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    private static boolean isOdd(int x) {
        return !isEven(x);
    }

}
