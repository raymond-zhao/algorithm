package com.kuaishou.raymond.algorithm.swordtooffer.day22_bit_operation_medium_star;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个整形数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字，要求时间复杂度是 O(n), 空间复杂度是 O(1).
 * 一个数字与自身异或的结果为 0，如果将数组中的所有元素进行异或，结果等于两个只出现一次的数字的异或结果。
 * 如 4 ^ 1 ^ 4 ^ 6 = 1 ^ 6 = 0001 ^ 0110 = 0111 = 7
 */
public class P56_1 {

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 4, 6}; // [1,6] 或 [6,1]
        int[] nums2 = {1, 2, 10, 4, 1, 4, 3, 3}; // [2,10] 或 [10,2]
        P56_1 p561 = new P56_1();
        System.out.println(
                "Arrays.toString(p561.singleNumbers(nums1)) = " + Arrays.toString(p561.singleNumbers(nums1)));
        System.out.println(
                "Arrays.toString(p561.singleNumbersV2(nums1)) = " + Arrays.toString(p561.singleNumbersV2(nums1)));
    }

    public int[] singleNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[2];
        int idx = 0;
        for (int num : nums) {
            if (map.get(num) == 1) {
                res[idx++] = num;
            }
        }
        return res;
    }

    public int[] singleNumbersV2(int[] nums) {
        int oplusResult = 0; // 所有数字异或的结果
        for (int num : nums) {
            oplusResult = oplusResult ^ num;
        }
        int mask = 1;
        while ((mask & oplusResult) == 0) {
            // 构建掩码
            mask = mask << 1;
        }

        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                x = x ^ num;
            } else {
                y = y ^ num;
            }
        }

        return new int[] {x, y};
    }
}
