package com.kuaishou.raymond.algorithm.sword.to.offer1.day23_math_easy;

import java.util.Arrays;

/**
 * 构建乘积数组
 * 给定一个数组 A，返回一个数组 B，其中 B[i]=数组 A 中除 A[i] 意外所有数字的乘积。
 * 要求：不能使用除法
 */
public class P66 {

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5}; // B=[120, 60, 40, 30, 24]
        P66 p66 = new P66();
        System.out.println("Arrays.toString(p66.constructArr(A)) = " + Arrays.toString(p66.constructArr(A)));
    }

    /**
     * 不能用除法，那就用两次乘法，第一次乘法先把 A[0]~A[i-1] 乘起来，第二次乘法把 A[i+1]~A[n-1] 乘起来，
     * 最后再把两边乘起来。
     */
    public int[] constructArr(int[] nums) {
        int[] b = new int[nums.length];
        b[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 当前元素左边所有元素的乘积
            // 第一个元素不乘以自己，所以从第二个元素，即 nums[1] 开始算，b[1]=nums[0]*b[0]
            // nums[i-1] 代表的是当前元素的上一个元素，
            // b[i-1] 代表的是当前元素上上个元素及其之前的所有元素的积。
            b[i] = b[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 当前元素右边所有元素的乘积
            temp = temp * nums[i + 1];
            // b[i] 是之前 nums[0..i-1] 的乘积，现在再乘上 nums[i+1].
            b[i] = b[i] * temp;
        }
        return b;
    }

    public int[] constructArrV2(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }

        int[] b = new int[a.length]; // 左乘积数组
        b[0] = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }

        int[] c = new int[a.length]; // 右乘积数组
        c[a.length - 1] = 1;
        for (int j = a.length - 2; j >= 0; j--) {
            c[j] = c[j + 1] * a[j + 1];
        }

        for (int k = 0; k < a.length; k++) {
            // 左乘积数组 * 右乘积数组
            b[k] *= c[k];
        }

        return b;
    }

}
