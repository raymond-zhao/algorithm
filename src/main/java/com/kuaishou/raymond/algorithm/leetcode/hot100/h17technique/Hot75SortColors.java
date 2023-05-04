package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.swap;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 14:42
 * 题目：<a href="https://leetcode.cn/problems/sort-colors/?envType=study-plan-v2&id=top-100-liked">75. 颜色分类</a>
 * - 双指针
 */
public class Hot75SortColors {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[2,0,2,1,1,0]");
        sortColors(nums);
        AlgoUtils.printRow(nums);
    }

    public static void sortColors(int[] nums) {
        int zeroIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ++zeroIdx;
                swap(nums, zeroIdx, i);
            }
        }
        int oneIdx = zeroIdx;
        for (int i = zeroIdx + 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                ++oneIdx;
                swap(nums, oneIdx, i);
            }
        }
    }

}
