package com.kuaishou.raymond.algorithm.leetcode.hot100.h11binarysearch;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 13:14
 * 题目：<a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/?envType=study-plan-v2&id=top-100-liked">4. 寻找两个正序数组的中位数</a>
 * 要求：时间复杂度O(log(m+n))
 * - Hard
 */
public class Hot4MedianOfTwoSortedArray {

    /**
     * 如果不限定时间复杂度的话，可以先合并两个有序数组，然后再寻找中位数，
     * 这种方法时间与空间复杂度均为 O(m+n).
     * 看到时间复杂度为对数级别，并且与有序数组有关，首先想到二分思想，每次操作排除掉一半的元素，
     * 那么，本题中这个可以排除掉一半元素的条件应该是什么呢？
     * 对于含有奇数个（n）元素的数组而言，求中位数实际上是在求第 Ceil(n/2) 个元素；
     * 对于含有偶数个（n）元素的数组而言，求中位数实际上是在求第 n/2 与 第 Ceil(n/2) 个元素的平均值；
     * 所以，本题也可以转化为求排序数组中第 K 小的元素的问题。
     * 我们可以每次比较 nums1[middle] 与 nums2[middle]，
     * 如果 nums1[middle] <= nums2[middle]，那么 nums1[middle] 之前的所有元素也小于 nums2[middle]，可以排除掉 m/2 个元素，
     * 同理，如果 nums1[middle] > nums2[middle]，那么可以排除掉 nums2 中的 n/2 个元素。
     * 在这个过程中，我们还需要维护 K 与排除掉的元素个数之间的关系。
     * 具体的，
     * 1. 分别设置两个指针从 nums1，nums2 的起始位置开始搜索，每次前进 k/2 个位置，
     * 2. 比较 nums1[index1] 与 nums2[index2]，排除掉较小元素及其之前的所有元素，共 newIndex - index + 1 个元素，同时使 k 较小相应的个数，索引的开始位置更新为新索引的下一个位置。
     * 3. 特别的，当某索引已经走到头使，直接返回另一个数组中的 index + k - 1 这个位置；如果 k=1，则返回两数组头部元素中的较小值。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 1) {
            return findKthSmallestElement(nums1, nums2, totalLength / 2 + 1);
        }
        return (findKthSmallestElement(nums1, nums2, totalLength / 2) + findKthSmallestElement(nums1, nums2, totalLength / 2 + 1)) / 2.0;
    }

    /**
     * 寻找两个有序数组中第 K 小的数字
     * @param nums1 有序数组
     * @param nums2 有序数组
     */
    private double findKthSmallestElement(int[] nums1, int[] nums2, int k) {
        // nums1 与 nums2 的起始搜索位置
        int index1 = 0;
        int index2 = 0;
        while (true) {
            // 如果 index1 已经搜索到 nums1 的尽头，排除掉了 nums1.length 个元素，则第 k 小一定在 nums2 中。
            if (index1 == nums1.length) {
                return nums2[index2 + k - 1];
            }
            if (index2 == nums2.length) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 计算下一轮搜索的索引
            int newIndex1 = Math.min(nums1.length, index1 + k / 2) - 1;
            int newIndex2 = Math.min(nums2.length, index2 + k / 2) - 1;
            // 比较两个数组的头部元素，排除掉其中较小元素及其之前的所有值。
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                // 排除 newIndex1 及其之前的所有元素
                k -= (newIndex1 - index1 + 1);
                // newIndex1 及其之前的所有元素已被排除，下一轮搜索位置为 newIndex1+1。
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

}
