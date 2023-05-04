package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 14:38
 * 题目：<a href="https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&id=top-100-liked">169. 多数元素</a>
 * - 摩尔投票法
 */
public class Hot169MajorityElement {

    public int majorityElement(int[] nums) {
        int majorityElement = nums[0];
        int votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                majorityElement = num;
            }
            votes += num == majorityElement ? 1 : -1;
        }
        return majorityElement;
    }

}
