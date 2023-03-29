package com.kuaishou.raymond.algorithm.sword.to.offer1.day23_math_easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过了数组总长度的一半，找出这个元素。
 */
public class P39 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        P39 p39 = new P39();
        System.out.println("p39.majorityElement(nums) = " + p39.majorityElement(nums));
        System.out.println("p39.majorityElementV2(nums) = " + p39.majorityElementV2(nums));
        System.out.println("p39.majorityElementV3(nums) = " + p39.majorityElementV3(nums));
    }

    /**
     * 数组中出现次数最多的元素在数学中被称为众数
     * 很直观的解决方式是遍历数组并使用哈希表存储每个元素出现的次数。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        int majorityElement = nums[0];
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > length / 2) {
                return num;
            }
            map.put(num, count);
        }
        return majorityElement;
    }

    /**
     * 先对数组排序，然后再取数组 len/2 位置处的元素。
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     */
    public int majorityElementV2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2 + 1];
    }

    /**
     * 摩尔投票法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int majorityElementV3(int[] nums) {
        int majorityElement = nums[0];
        int votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                majorityElement = num;
            }
            int vote = num == majorityElement ? 1 : -1;
            votes = votes + vote;
        }

        // 如果不保证数组中的这个"众数一定存在"，需要遍历数组统计其次数，再判断其出现次数是否大于数组长度的一半。
        int count = 0;
        for (int num : nums) {
            if (num == majorityElement) {
                count++;
            }
        }
        if (count < nums.length / 2) {
            throw new NoSuchElementException();
        }
        return majorityElement;
    }
}
