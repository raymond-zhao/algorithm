package com.kuaishou.raymond.algorithm.swordtooffer.day4_search_easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 */
public class P53Search {

    public static void main(String[] args) {
        P53Search p53 = new P53Search();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 10;
        System.out.println("p53.search(nums, target) = " + p53.search(nums, target));
        System.out.println("p53.binarySearch(nums, target) = " + p53.search2(nums, target));
    }

    /**
     * 利用"排序数组"这个条件，进行二分搜索。
     * 定位到 target 之后，在其右侧继续二分，寻找 [target, \infty) 的左边界 right；
     * 定位到 target 之后，在其左侧继续二分，寻找 [-\infty, target) 的右边界 left；
     * 则 target 出现的次数 = right - left + 1
     *
     * 或者，两次二分搜索，一次找到左边界，一次找到右边界。
     */
    public int binarySearch(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int middle = (i + j) >>> 1;
            if (nums[middle] <= target) {
                i = middle + 1;
            } else if (nums[middle] > target) {
                j = middle - 1;
            }
        }
        return i;
    }

    public int search2(int[] nums1, int target) {
        return binarySearch(nums1, target) - binarySearch(nums1, target - 1);
    }

    /**
     * 时间复杂度 O(n), 空间复杂度 O(n)。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.get(target);
    }

}
