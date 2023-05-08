package com.kuaishou.raymond.algorithm.leetcode.hot100.h1hash;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&id=top-100-liked">128. 最长连续序列</a>
 * - 哈希
 * 要求：以时间复杂度 O(n) 解决此问题
 */
public class Hot128LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 1};
        int[] nums2 = {100, 4, 200, 1, 3, 2};
        System.out.println("longestConsecutive(nums) = " + longestConsecutive(nums));
        System.out.println("longestConsecutive(nums2) = " + longestConsecutive(nums2));
    }

    /**
     * 1. 遍历数组，将数组中的所有元素存入哈希表，以 O(1) 的时间用于后续的比较。
     * 2. 再次遍历数组，寻找其中不存在 -1 小的那个元素（ !set.contains(num-1) ）
     * 3. 找到最小元素之后，从集合中判断是否存在 num+1，如果存在，则更新最长连续序列的长度。
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 将所有元素加入 Set 去重，并且之后可在 O(1) 时间内判断某元素是否在其中。
            set.add(num);
        }
        int maxLength = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                // 如果集合中包含比当前值小 1 的数字，说明当前数字并不是连续序列的起点。
                continue;
            }
            // 此时遍历到的 num，是连续序列中最小的那个，从它开始每次+1进行枚举。
            int currentLength = 1;
            while (set.contains(++num)) {
                currentLength += 1;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

}
