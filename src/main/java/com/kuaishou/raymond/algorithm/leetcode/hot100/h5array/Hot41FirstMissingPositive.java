package com.kuaishou.raymond.algorithm.leetcode.hot100.h5array;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/first-missing-positive/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 41. 缺失的第一个正数
 */
public class Hot41FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toArray("[7,8,9,11,12]");
        System.out.println("firstMissingPositive(nums) = " + firstMissingPositive(nums));
    }

    /**
     * 手动实现哈希
     * 哈希效果：将 nums[i] 放置到索引为 i+1 的位置
     * 因为数组不能越界，所以只能针对 nums[i] \in [1, len] 中的数字进行交换。
     * 如果某个数字不在数组长度范围之内，也就是说 nums[i] < 0 或者 nums[i] > len，则不会交换。
     * 数组的下标范围为：[0, len-1]
     * 当我们第二次遍历数组的时候，检查 nums[i] - 1 == i 这个条件是否满足，
     * 如果不满足，说明这个「索引 + 1」表示的正数并未出现过，直接返回。
     * 如果索引中的数字全部都满足 nums[i]-1==i 这个条件，也就是 [1,2,3,4,5,...] 这种情况，缺失的数字则为数组长度 + 1.
     */
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (1 <= nums[i] && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < len; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return len + 1;
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
