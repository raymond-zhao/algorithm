package com.kuaishou.raymond.algorithm.leetcode.all;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-24 14:32
 * <a href="https://leetcode.cn/problems/jump-game-ii/">...</a>
 */
public class P45JumpGameII {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4}; // 2
        int[] nums2 = {2, 3, 0, 1, 4}; // 2
        System.out.println("jump(nums) = " + jump(nums));
        System.out.println("jumpV2(nums) = " + jumpV2(nums));
    }

    /**
     * 反向倒推：从最左侧开始以贪心算法寻找第一个可以跳跃到目标位置 targetPosition 的地方，然后将下一次的目标位置更新为当前位置。
     */
    public static int jump(int[] nums) {
        int targetPosition = nums.length - 1;
        int steps = 0;
        while (targetPosition > 0) {
            for (int idx = 0; idx < targetPosition; idx++) {
                // 如果从当前位置可以跳跃到 targetPosition 位置
                if (idx + nums[idx] >= targetPosition) {
                    // 则把跳跃起点更点为当前位置
                    targetPosition = idx;
                    // 跳跃次数 + 1
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * 正向扫描：
     */
    public static int jumpV2(int[] nums) {
        int steps = 0;
        int maxPosition = 0;
        int endPosition = 0;
        // 不访问最后一个位置
        for (int idx = 0; idx < nums.length - 1; idx++) {
            maxPosition = Math.max(maxPosition, idx + nums[idx]);
            if (idx == endPosition) {
                endPosition = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
