package com.kuaishou.raymond.algorithm.leetcode.all;

public class P55JumpGameI {

    public boolean canJump(int[] nums) {
        int maxPosition = 0;
        for (int currentPosition = 0; currentPosition < nums.length; currentPosition++) {
            if (currentPosition <= maxPosition) {
                maxPosition = Math.max(maxPosition, currentPosition + nums[currentPosition]);
                if (maxPosition >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
