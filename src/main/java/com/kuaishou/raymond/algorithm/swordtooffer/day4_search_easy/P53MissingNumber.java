package com.kuaishou.raymond.algorithm.swordtooffer.day4_search_easy;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入: [0,1,3]
 * 输出: 2
 */
public class P53MissingNumber {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 5};
        P53MissingNumber p53 = new P53MissingNumber();
        System.out.println("p53.missingNumber(nums) = " + p53.missingNumber(nums));
    }

    public int missingNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int[] helper = new int[nums.length + 1];

        for (int num : nums) {
            helper[num] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < helper.length; i++) {
            if (helper[i] != Integer.MIN_VALUE) {
                return i;
            }
        }
        return -1;
    }

}
