package com.kuaishou.raymond.algorithm.sword.to.offer1.day22_bit_operation_medium_star;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次，找出那个只出现一次的数字。
 * nums.length \in [1, 10000]
 * nums[i] \in [1, 2^31)
 */
public class P56_2 {

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 3, 3};
        int[] nums2 = {9, 1, 7, 9, 7, 9, 7};
        P56_2 p562 = new P56_2();
        System.out.println("p562.singleNumber(nums1) = " + p562.singleNumber(nums1));
        System.out.println("p562.singleNumberV2(nums1) = " + p562.singleNumberV2(nums1));
        System.out.println("p562.singleNumberV3(nums1) = " + p562.singleNumberV3(nums1));
        System.out.println("p562.singleNumber(nums2) = " + p562.singleNumber(nums2));
        System.out.println("p562.singleNumberV2(nums2) = " + p562.singleNumberV2(nums2));
        System.out.println("p562.singleNumberV3(nums2) = " + p562.singleNumberV3(nums2));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n/3 + 1) = O(n)
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
            if (count == 3) {
                map.remove(num);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new NoSuchElementException();
    }

    /**
     * 统计 int 数字 32 个比特位中 1 的个数，如果某个比特为上 1 或 0 的和不等于 3，
     */
    public int singleNumberV2(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] = counts[j] + (num & 1);
                num = num >>> 1;
            }
        }
        // counts 中已存储所有数位的和
        // 接下来还原这个只出现 1 次的值
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res = res | (counts[31 - i] % 3);
        }
        return res;
    }

    /**
     * 最优解
     */
    public int singleNumberV3(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
