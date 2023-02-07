package com.kuaishou.raymond.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-13 09:47
 */
@Slf4j
public class RadixSort {

    public static void main(String[] args) {
        int[] nums1 = {329, 457, 657, 839, 436, 720, 355};
        int[] array = nums1;
        log.info("基数排序 = {}", radixSort(array, 3));
    }

    /**
     * 从 d 位数的个位起，依次对每一位使用稳定排序算法。
     */
    public static int[] radixSort(int[] array, int d) {
        for (int i = 1; i <= d; i++) {
            for (int j = 0; j < array.length; j++) {
                int resident = array[j] % 10;
                log.info("resident = {}", resident);
                // countingSort(array, 10);
                array[j] = array[j] / 10;
            }

            System.out.println();
        }
        return array;
    }
}
