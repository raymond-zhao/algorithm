package com.kuaishou.raymond.algorithm.leetcode.hot100.bipointer;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.convertToArray;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-27 11:36
 * <a href="https://leetcode.cn/problems/move-zeroes/">...</a>
 */
public class Hot283MoveZeros {

    public static void main(String[] args) {
        int[] nums = convertToArray("[1,0,1,12,0]");
        AlgoUtils.printRow(nums);
//        moveZeroes(nums);
        moveZeroesII(nums);
        AlgoUtils.printRow(nums);
    }

    /**
     * 双指针：会改变顺序
     */
    public static void moveZerosV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0) {
                left++;
            }
            while (left < right && nums[right] == 0) {
                right--;
            }
            swap(nums, left, right);
        }
    }

    public static void moveZeroesII(int[] nums) {
        int nonZeroIdx = -1;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                ++nonZeroIdx;
                swap(nums, nonZeroIdx, fast);
            }
        }
    }

    /**
     * 双指针：不会改变非零元素的相对顺序
     */
    public static void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                swap(nums, slow, fast);
                ++slow;
            }
            ++fast;
        }
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
