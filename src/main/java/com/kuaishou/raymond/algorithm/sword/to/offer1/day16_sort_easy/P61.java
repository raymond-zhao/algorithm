package com.kuaishou.raymond.algorithm.sword.to.offer1.day16_sort_easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这 5 张牌是不是连续的。
 * 2～10 为数字本身，A 为 1，J 为 11，Q 为 12，K 为13，而大、小王为 0，可以看成任意数字。A 不能视为 14。
 * ---
 */
public class P61 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {0, 0, 1, 2, 5};
        int[] nums3 = {0, 0, 2, 2, 5};
        int[] nums4 = {11, 8, 12, 8, 10};
    }

    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        int min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        return max - min < 5;
    }
}
