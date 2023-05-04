package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 14:36
 * 题目：<a href="https://leetcode.cn/problems/single-number/?envType=study-plan-v2&id=top-100-liked">136. 只出现一次的数字</a>
 */
public class Hot136SingleNumber {

    public int singleNumber(int[] nums) {
        int singleNumber = 0;
        for (int num : nums) {
            singleNumber ^= num;
        }
        return singleNumber;
    }

}
