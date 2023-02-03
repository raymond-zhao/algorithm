package com.kuaishou.raymond.algorithm.swordtooffer.day4_search_easy;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof"><a/>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class P3FindRepeatNumber {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int[] nums2 = {3, 4, 2, 1, 1, 0};
        P3FindRepeatNumber p3 = new P3FindRepeatNumber();
        System.out.println("p3.findRepeatNumber(nums) = " + p3.findRepeatNumber(nums2));
        System.out.println("p3.findRepeatNumber(nums) = " + p3.findRepeatNumber2(nums2));
    }

    /**
     * 就地修改，数组下标应该与其存储的元素相同。
     * 依次遍历 nums 中元素，遍历到 nums[i] 时，时，将 nums[nums[i]] 的值先保存下来，
     * 检查 nums[nums[i]] ?= nums[i]，如果相等，则发现了重复数字，返回 nums[nums[i]];
     * 否则，执行 nums[nums[i]] = nums[i]，将 nums[nums[i]] 归位，使其元素值与其数组下标相等。
     * 如 [2, 3, 1, 0, 2, 5, 3]，
     */
    public int findRepeatNumber2(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int i = 0;
        while (i < nums.length) {
            if (i == nums[i]) {
                i++;
                continue;
            }
            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            swap(nums, i, nums[i]);
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 辅助数组
     * nums1 中的元素值 = helper 中的数组下标
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int[] helper = new int[nums.length];

        for (int num : nums) {
            helper[num] = helper[num] + 1;
        }

        for (int i = 0; i < helper.length; i++) {
            if (helper[i] >= 2) {
                return i;
            }
        }
        return -1;
    }

}
