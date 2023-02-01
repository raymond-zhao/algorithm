package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-01 13:13
 * 如果不要求 O(log(m+n)) 的话
 * 1. 合并两个有序数组，取中位数；O(m+n)
 * 2. 双指针(下标之和是固定的),两个指针 p1, p2 均从 0 开始:
 *    奇数：p1 + p2 = (m + n) / 2，当 p1 + p2 满足奇偶数量下的中位数条件下时即为结果
 *    偶数：p1 + p2 = () 每次将指向较小数的指针后移一位 O(m+n)
 * 本题也可以转为：
 * 在奇数场景下，求数组中第 (m+n)/2 小的元素；
 * 在偶数场景下，求数组中第 (m+n)/2 - 1 小与第 (m+n)/2 小的元素。
 *
 * [], [0,1,2,3], 中位数=(1+2)/2=1.5, 索引下标之和=1+2=3
 * [0], [1,2,3], 中位数=(1+2)/2=1.5, 索引下标之和=0+1=1
 * [0,1], [2,3], 中位数=(1+2)/2=1.5，索引下标之和=1+0=1
 * [0,2], [1,3], 中位数=(1+2)/2=1.5，索引下标之和=1+0=1
 * [0,3], [1,2], 中位数=(1+2)/2=1.5，索引下标之和=1+0=1
 *
 */
public class P4FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        P4FindMedianSortedArrays p4FindMedianSortedArrays = new P4FindMedianSortedArrays();
        System.out.println("p4FindMedianSortedArrays.findMedianSortedArrays(nums1, nums2) = "
                + p4FindMedianSortedArrays.findMedianSortedArrays(nums1, nums2));

        System.out.println("p4FindMedianSortedArrays.findMedianSortedArrays(nums3, nums4) = "
                + p4FindMedianSortedArrays.findMedianSortedArrays(nums3, nums4));

        System.out.println("p4FindMedianSortedArrays.findMedianOfMergedArrays(nums1, nums2) = "
                + p4FindMedianSortedArrays.findMedianOfMergedArrays(nums1, nums3));

        System.out.println("p4FindMedianSortedArrays.findMedianOfTwoSortedArraysUsingTwoPointers(nums1, nums2) = "
                + p4FindMedianSortedArrays.findMedianOfTwoSortedArraysUsingTwoPointers(nums1, nums3));
    }


    public double findMedianOfTwoSortedArraysUsingTwoPointers(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        int p1 = 0, p2 = 0;

        if (((m + n) % 2) == 1) {
            // 总共奇数个元素，中位数 = 第 (m+n)/2 小的元素
            while (p1 + p2 < (m + n) / 2) {
                if (nums1[p1] <= nums2[p2]) {
                    ++p1;
                } else {
                    ++p2;
                }
            }
            return Math.min(nums1[p1], nums2[p2]);
        }

        while (p1 + p2 < m + n - 1) {
            if (nums1[p1] <= nums2[p2]) {
                ++p1;
            } else {
                ++p2;
            }
        }
        return ((double) nums1[p1] + (double) nums2[p2]) / 2;
    }

    public double findMedianOfMergedArrays(int[] nums1, int[] nums2) {
        int[] mergedArray = mergeTwoSortedArrays(nums1, nums2);
        int length = mergedArray.length;
        if (length % 2 == 1) {
            return mergedArray[length / 2];
        }
        return ((double) mergedArray[length / 2 - 1] + (double) mergedArray[length / 2]) / 2;
    }

    public int[] mergeTwoSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }
        if (nums2.length == 0) {
            return nums1;
        }

        int m = nums1.length, n = nums2.length;
        int[] res = new int[m + n];

        int p1 = 0, p2 = 0, p3 = 0;
        while (p1 < m && p2 < n) {
            res[p3++] = nums1[p1] <= nums2[p2] ? nums1[p1++] : nums2[p2++];
        }

        while (p1 < m) {
            // nums1 没遍历完，剩下的值直接拼装到 res。
            res[p3++] = nums1[p1++];
        }

        while (p2 < n) {
            // nums2 没遍历完，剩下的值直接拼装到 res。
            res[p3++] = nums2[p2++];
        }

        return res;
    }

    /**
     * 寻找两个有序数组的中位数，要求算法复杂度应该为 O(log(m+n))
     * 长度为 m + n 的数组中位数：
     *   1. 如果 m + n = 奇数，则中位数下标 mid = (m + n) / 2，如 mid = (3 + 2) / 2 = 2;
     *   2. 如果 m + n = 偶数，则中位数下标 mid1 = (m + n) / 2 - 1 与 mid2 = (m + n) / 2 共同决定。
     *      如 mid1 = (2 + 2) / 2 - 1 = 1，mid2 = (2 + 2) / 2 = 2;
     * 因为两个数组均有序，所以可考虑同时在两个数组中进行二分查找。
     * @param nums1 len=m
     * @param nums2 len=n
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return Integer.MIN_VALUE;
        }

        return 0;
    }

}
