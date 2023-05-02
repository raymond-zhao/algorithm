package com.kuaishou.raymond.algorithm.leetcode.hot100.h14greedy;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 23:47
 * 题目：<a href="https://leetcode.cn/problems/jump-game-ii/?envType=study-plan-v2&id=top-100-liked">45. 跳跃游戏 II</a>
 */
public class Hot45JumpGameII {

    public int jump(int[] nums) {
        int steps = 0;
        int maxPosition = 0;
        int lastJumpMaxPosition = maxPosition;
        for (int currentPosition = 0; currentPosition < nums.length - 1; currentPosition++) {
            int jumpDistance = nums[currentPosition];
            maxPosition = Math.max(maxPosition, currentPosition + jumpDistance);
            if (currentPosition == lastJumpMaxPosition) {
                // 只有在当前跳跃位置达到了之前跳跃步骤中能达到的最大距离时，才准备开始下一跳，尽量减少跳跃次数。
                lastJumpMaxPosition = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
