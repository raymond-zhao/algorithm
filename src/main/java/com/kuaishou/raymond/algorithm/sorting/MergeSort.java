package com.kuaishou.raymond.algorithm.sorting;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-03 17:06
 */
public class MergeSort {

    public static void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int middle = (left + right) >>> 1;
            mergeSort(nums, left, middle);
            mergeSort(nums, middle + 1, right);
            merge(nums, left, middle, right);
        }
    }

    private static void merge(int[] nums, int left, int middle, int right) {
        // 存放本轮归并的结果
        int[] tempArray = new int[right - left + 1];
        int l = left;
        int r = middle + 1;
        int k = 0;

        while (l <= middle && r <= right) {
            // 比较并归并子数组中的数据
            if (nums[l] <= nums[r]) {
                tempArray[k++] = nums[l++];
            } else {
                tempArray[k++] = nums[r++];
            }
        }
        // 此时 l > middle || r > right，数组可能未归并完成，需要拷贝未归并的数据。
        while (l <= middle) {
            tempArray[k++] = nums[l++];
        }

        while (r <= right) {
            tempArray[k++] = nums[r++];
        }

        // 将排序后的子数组拷贝回原数组
        System.arraycopy(tempArray, 0, nums, left, tempArray.length);
    }
}
