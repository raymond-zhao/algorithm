package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

/**
 * https://leetcode.cn/problems/move-zeroes/
 *
 */
public class P283MoveZeros {

    /**
     * [0,1,0,3,12]
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
        }
    }

}
