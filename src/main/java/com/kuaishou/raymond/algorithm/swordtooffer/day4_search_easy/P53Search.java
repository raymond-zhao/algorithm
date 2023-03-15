package com.kuaishou.raymond.algorithm.swordtooffer.day4_search_easy;

import java.util.Arrays;
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
        int target = 8;
        System.out.println("p53.search(nums, target) = " + p53.searchMap(nums, target));
        System.out.println("p53.binarySearch(nums, target) = " + p53.searchBinary(nums, target));
        p53.searchX(nums, 8);
    }

    public int searchBinary(int[] nums, int target) {
        return binarySearch(nums, target) - binarySearch(nums, target - 1);
    }

    /**
     * 利用"排序数组"这个条件，进行二分搜索。
     * 此二分查找结果为：大于 target 的第一个元素的位置
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] <= target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            }
        }
        return left;
    }

    /**
     * 时间复杂度 O(n), 空间复杂度 O(n)。
     * 统计任意一个字符出现的次数
     */
    public int searchMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.get(target);
    }

    public int searchParallelStream(int[] nums, int target) {
        return Math.toIntExact(Arrays.stream(nums).parallel().filter(num -> num == target).count());
    }

    public int searchBrutalForce(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    public int searchX(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                count += 1;
                int l = middle - 1;
                int r = middle + 1;
                while (l >= 0 && nums[l] == target) {
                    count++;
                    l--;
                }
                while (r <= right && nums[r] == target) {
                    count++;
                    r++;
                }
                break;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return count;
    }

}
