package com.kuaishou.raymond.algorithm.leetcode.all;

public class P55JumpGameI {

    public boolean canJump(int[] nums) {
        int maxPosition = 0;
        for (int currentPosition = 0; currentPosition < nums.length; currentPosition++) {
            if (currentPosition <= maxPosition) {
                // 如果当前位置还在之前的跳跃距离之内，则计算下一跳的最远距离。
                maxPosition = Math.max(maxPosition, currentPosition + nums[currentPosition]);
                if (maxPosition >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
