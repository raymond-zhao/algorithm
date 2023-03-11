package com.kuaishou.raymond.algorithm.leetcode;

import java.util.Arrays;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-08 13:15
 */
public class P198HouseRobbing {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {1, 2};
        int[] nums4 = {2, 3, 2};
        P198HouseRobbing p198 = new P198HouseRobbing();
        System.out.println("houseRobbing(nums) = " + p198.houseRobbing(nums));
        System.out.println("houseRobbing(nums2) = " + p198.houseRobbing(nums2));
        System.out.println("houseRobbing(nums3) = " + p198.houseRobbing(nums3));
        System.out.println("p198.houseRobbingSpaceCompressed(nums) = " + p198.houseRobbingSpaceCompressed(nums));
        System.out.println("p198.houseRobbingSpaceCompressed(nums2) = " + p198.houseRobbingSpaceCompressed(nums2));
        System.out.println("p198.houseRobbingSpaceCompressed(nums3) = " + p198.houseRobbingSpaceCompressed(nums3));
        System.out.println("p198.houseRobbingV2(nums4) = " + p198.houseRobbingV2(nums4));
        System.out.println("p198.houseRobbingV2(nums) = " + p198.houseRobbingV2(nums));
    }

    /**
     * 动态规划 1
     */
    public int houseRobbing(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = Math.max(0, nums[0]);
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 动态规划：空间压缩
     * 在 {@link P198HouseRobbing#houseRobbing(int[])} 中，使用了长度为 n 的 dp 数组，
     * 但实际上，迭代求取 dp 的过程中，只依赖 dp[i - 1] 与 dp[i - 1]，所以可以只使用两个变量，交替进行。
     */
    public int houseRobbingSpaceCompressed(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int currentMax = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = currentMax;
        }
        return prev1;
    }

    /**
     * 打家劫舍 2：房屋首位相连
     */
    public int houseRobbingV2(int[] nums) {
        return Math.max(
                houseRobbingSpaceCompressed(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                houseRobbingSpaceCompressed(Arrays.copyOfRange(nums, 1, nums.length))
        );
    }

    /**
     * 打家劫舍 3：树形房屋
     */
    public int houseRobbingV3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 0;
    }
}
