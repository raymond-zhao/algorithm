package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-01 13:13
 *
 */
public class P4FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        // int[] nums2 = {2};
        // int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        P4FindMedianSortedArrays p4FindMedianSortedArrays = new P4FindMedianSortedArrays();
        System.out.println("p4FindMedianSortedArrays.findMedianSortedArrays(nums1, nums2) = "
                + p4FindMedianSortedArrays.findMedianOfMergedArrays(nums1, nums4));

        System.out.println("p4FindMedianSortedArrays.findMedianOfTwoSortedArrays(nums1, nums2) = "
                + p4FindMedianSortedArrays.findMedianOfTwoSortedArrays(nums1, nums4));;
    }

    public double findMedianOfTwoSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 1) {
            int medianIndex = (m + n) / 2;
            return findKthElement(nums1, nums2, medianIndex + 1);
        } else {
            int medianIndex1 = (m + n) / 2 - 1;
            int medianIndex2 = (m + n) / 2;
            return (findKthElement(nums1, nums2, medianIndex1 + 1) + findKthElement(nums1, nums2, medianIndex2 + 1)) / 2.0;
        }
    }

    public double findKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        // 从数组起始位置开始搜索
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界条件
            if (index1 == len1) {
                // 索引下标与数组长度相等，说明数组 nums1 中已经没有元素，第 k 小的元素只会出现在 nums2 中，
                // 而 nums2 中从 index2 开始的第 k 小元素的索引为 index2 + k - 1，如 A=[], B=[2,3,4], k=2
                // 则 B 中第 2 小的元素的下标为 0 + 2 - 1 = 1，即 B[1]=3。
                return nums2[index2 + k - 1];
            }

            if (index2 == len2) {
                // 索引下标与索引长度相等，说明 nums2 中已经没有元素， 第 k 小的元素只会出现在 nums1 中，
                // 而 nums1 中从 index1 开始的第 k 小的索引为 index1 + k - 1。
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                // 如果 A 没有走到头，B 没有走到头，并且要寻找的是两个数组中最小的元素。
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 普通条件
            // 计算下一次要搜索的索引位置，加上 k/2，排除掉 k/2 - 1 个元素。
            int newIndex1 = Math.min(len1, index1 + k / 2) - 1;
            int newIndex2 = Math.min(len2, index2 + k / 2) - 1;

            if (nums1[newIndex1] <= nums2[newIndex2]) {
                // 如果 A 的起始位置值不大于 B 的起始位置的值，那 A[index1 ... newIndex1] 之间的所有值也不大于 B 起始位置的值。
                // 此时，可以排除掉从老索引 index1 到新索引 newIndex1 之间的 newIndex1 - index1 + 1 个元素。
                k = k - (newIndex1 - index1 + 1);
                // 因为 A[0...newIndex] 之间的所有元素都已经被排除掉，所以下次从 newIndex + 1 这个位置开始搜索。
                index1 = newIndex1 + 1;
            } else {
                // 同理，如果 A 起始位置的值大于 B 起始位置的值，那 B[index2 ... newIndex2] 之间的所有值也都小于 A 起始位置的值。
                // 此时，可以排除掉 B 中从 index2 到 newIndex2 之间的 newIndex2 - index2 + 1 个元素。
                k = k - (newIndex2 - index1 + 1);
                index2 = newIndex2 + 1;
            }
        }
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

}
