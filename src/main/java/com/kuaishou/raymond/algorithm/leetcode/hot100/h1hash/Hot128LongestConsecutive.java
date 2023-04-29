package com.kuaishou.raymond.algorithm.leetcode.hot100.h1hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 128. 最长连续序列
 * 要求：以时间复杂度 O(n) 解决此问题
 */
public class Hot128LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 1};
        int[] nums2 = {100, 4, 200, 1, 3, 2};
        System.out.println("longestConsecutiveV2(nums) = " + longestConsecutiveV2(nums));
        System.out.println("longestConsecutiveV2(nums2) = " + longestConsecutiveV2(nums2));
        System.out.println("longestConsecutive(nums) = " + longestConsecutive(nums));
        System.out.println("longestConsecutive(nums2) = " + longestConsecutive(nums2));
    }

    /**
     * 排序：0, 1, 1, 2
     * 结果集：0, 1, 2
     */
    public static int longestConsecutiveV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxLength = 1;
        int previous = nums[0];
        int curLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == previous) {
                continue;
            }
            if (nums[i] == previous + 1) {
                curLength++;
            } else {
                maxLength = Math.max(maxLength, curLength);
                curLength = 1;
            }
            previous = nums[i];
        }
        maxLength = Math.max(maxLength, curLength);
        return maxLength;
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 将所有元素加入 Set 去重，并且之后可在 O(1) 时间内判断某元素是否在其中。
            set.add(num);
        }
        int maxLength = 0;
        for (int num : set) {
            // 如果不存在比当前值小的元素，则开始寻找。
            if (!set.contains(num - 1)) {
                // 此时，num 已是 nums 中最小的元素。
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                maxLength = Math.max(maxLength, currentStreak);
            }
        }
        return maxLength;
    }

}
