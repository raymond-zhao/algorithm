package com.kuaishou.raymond.algorithm.leetcode.hot100.h17technique;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.reverse;
import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.swap;

/**
 * Author: raymond
 * CreateTime: 2023/5/4 14:58
 * 题目：<a href="https://leetcode.cn/problems/next-permutation/?envType=study-plan-v2&id=top-100-liked">31. 下一个排列</a>
 */
public class Hot31NextPermutation {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[3,2,1]");
        int[] intArray = AlgoUtils.toIntArray("[2,3,1]");
        nextPermutation(intArray);
        AlgoUtils.printRow(intArray);
    }

    public static void nextPermutation(int[] nums) {
        int smallerIdx = nums.length - 2;
        // 从右向左寻找第一个升序对
        while (smallerIdx >= 0 && nums[smallerIdx] >= nums[smallerIdx + 1]) {
            --smallerIdx;
        }
        // 此时，nums[smallerIdx] < nums[smallerIdx+1]，
        // 并且 [smallerIdx+1, len) 之间的元素单调递减，如 [1,4,3,2]。

        int largerIdx = nums.length - 1;
        if (smallerIdx >= 0) {
            // 接下来，从右向左寻找大于并且是第一个大于 nums[smallerIdx] 的元素，与 smallIdx 交换。
            while (largerIdx >= 0 && nums[largerIdx] <= nums[smallerIdx]) {
                --largerIdx;
            }
            // 此时，nums[largerIdx] > nums[smallerIdx]，如 [1,4,3,2] 中，smallerIdx=0, largerIdx=3。
            swap(nums, smallerIdx, largerIdx);
            // 交换后，数组变为 [2,4,3,1]，其中 [smallerIdx+1, len) 仍然单调递减，也就是说 [4,3,1] 是最大的排列。
            // 为了让排列最小，还需要让 {4,3,1} 变为最小值，也就是单调递增，
            // 所以需要进行翻转，这个动作在 if 语句之外，与 smallerIdx < 0 的情况复用。
        }

        // 如果 smallerIdx < 0，则说明从 [0..len-1] 单调递减，
        // 如 [4,3,2,1]，下一个更大排列为 [1,2,3,4]，也就是反转所有元素。
        reverse(nums, smallerIdx + 1, nums.length - 1);
    }
}
