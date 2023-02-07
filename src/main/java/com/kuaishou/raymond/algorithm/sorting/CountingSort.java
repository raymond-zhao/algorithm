package com.kuaishou.raymond.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-11 17:20
 */
@Slf4j
public class CountingSort {

    public static void main(String[] args) {
        // int[] nums1 = {2, 5, 3, 0, 2, 3, 0, 3};
        int[] nums2 = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
        int[] array = nums2;
        log.info("计数排序 = {}", countingSort(array, 7));
    }

    public static int[] countingSort(int[] A, int k) {
        int[] C = new int[k];
        int[] B = new int[A.length];

        for (int aValueAsCIdx : A) {
            C[aValueAsCIdx] = C[aValueAsCIdx] + 1;
        }

        log.info("C1 = {}", C);
        for (int i = 1; i < k; i++) {
            // 此循环结束后，C 中每个索引所指向的数字，表示 A 中数据小于等于当前索引值的个数。
            // 如果 C[0] = 2，表示 A 中小于等于 0 的元素有 2 个。
            C[i] = C[i] + C[i - 1];
        }

        log.info("C2 = {}", C);
        // 根据 A 数组与 C 数组将 A 的排序结果输出到 C 数组
        for (int j = A.length - 1; j >= 0; j--) {
            int aValue = A[j];
            B[C[aValue] - 1] = aValue;
            C[aValue] = C[aValue] - 1;
        }

        return B;
    }
}
