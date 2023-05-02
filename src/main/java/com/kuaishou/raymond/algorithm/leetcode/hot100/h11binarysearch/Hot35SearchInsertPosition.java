package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 09:28
 * 题目：<a href="https://leetcode.cn/problems/search-insert-position/?envType=study-plan-v2&id=top-100-liked">35. 搜索插入位置</a>
 * 题解：<a href="https://leetcode.cn/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/">...</a>
 */
public class Hot35SearchInsertPosition {

    /**
     * 搜索插入位置，问题可以转换为：搜索大于等于target的第一个元素。
     * 使用左闭右闭区间，代码与二分搜索基本相同，但在最后返回值上有所差异，
     * 本题循环外的返回值是 left，表示大于 target 的第一个元素
     * 而二分循环外的返回值为 -1，表示没有搜索到等于 target 的元素。
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (nums[middle] == target) {
                // 或者 right=middle-1
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return left;
    }
}
