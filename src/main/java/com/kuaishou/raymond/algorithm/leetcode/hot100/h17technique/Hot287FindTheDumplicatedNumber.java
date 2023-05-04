package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.swap;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 19:03
 * 题目：<a href="https://leetcode.cn/problems/find-the-duplicate-number/?envType=study-plan-v2&id=top-100-liked">287. 寻找重复数</a>
 * 解析：<a href="https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/">...</a>
 */
public class Hot287FindTheDumplicatedNumber {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,3,4,2,2]");
        System.out.println("findDuplicate(nums) = " + findDuplicate(nums));
    }

    /**
     * 含有 n+1 个数字的数组 nums 中的元素取值范围在 [1..n] 之间，
     * 而数组的取值范围刚好在 [0..n] 之间，所以，可以考虑将数组中的每个元素，
     * 放置到以其值为下标的位置之上，比如把数字 1 放到索引 1，把数字 2 放到索引 2...
     * 当重复的数字 num 试图归位时，发现如果当前索引上的元素 num 已经与其相等，则表示找到了重复数字。
     */
    public static int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                // 如果以当前值为索引的位置上的值已经正确归位，则找到了重复数字。
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                }
                // 如果当前值未被正确放置到以其为下标的索引上，持续交换。
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    /**
     * 龟兔赛跑
     */
    public int findDuplicateII(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

}
