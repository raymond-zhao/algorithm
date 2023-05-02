package com.kuaishou.raymond.algorithm.leetcode.hot100.h14greedy;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 23:33
 * 题目：<a href="https://leetcode.cn/problems/jump-game/?envType=study-plan-v2&id=top-100-liked">55. 跳跃游戏</a>
 */
public class Hot55JumpGame {

    public boolean canJump(int[] nums) {
        int maxPosition = 0;
        for (int currentPosition = 0; currentPosition < nums.length; currentPosition++) {
            if (currentPosition <= maxPosition) {
                // 如果当前位置还在之前的跳跃距离之内，则计算下一跳的最远距离。
                int jumpDistance = nums[currentPosition];
                maxPosition = Math.max(maxPosition, currentPosition + jumpDistance);
                if (maxPosition >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
