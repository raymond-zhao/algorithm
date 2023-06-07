package com.kuaishou.raymond.algorithm.sorting;

import static com.kuaishou.raymond.algorithm.sorting.HeapSort.swap;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-11 14:24
 */
@Slf4j
public class QuickSort {

    public static void main(String[] args) {
        int[] nums1 = {2, 8, 7, 1, 3, 5, 6, 4, 4};
        int[] nums2 = AlgoUtils.toIntArray("[3,2,3,1,2,4,5,5,6]");
        int[] array = nums1;
        log.info("快速排序 = {}", quickSort(nums2, 0, array.length - 1));
    }

    /**
     * 尾递归
     */
    public static int[] tailRecursiveQuickSort(int[] array, int left, int right) {
        while (left < right) {
            int middle = lomutoPartition(array, left, right);
            tailRecursiveQuickSort(array, left, middle - 1);
            left = right + 1;
        }
        return array;
    }

    public static int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = hoarePartition(array, left, right);
            quickSort(array, left, middle - 1);
            quickSort(array, middle + 1, right);
        }
        return array;
    }

    /**
     * Lomuto 划分
     */
    private static int lomutoPartition(int[] array, int left, int right) {
        int key = array[right]; // 选择数组中最右元素作为基准元素
        int idx = left - 1; // idx 及其左侧的数均小于等于 key，所以 idx + 1 指向的是第一个大于 key 的数。

        for (int j = left; j < right; j++) {
            if (array[j] <= key) { // 等 j 找到小于等于 key 的数时，需要与大于 key，也就是 idx+1 指向的数字做交换。
                idx = idx + 1; // 位于 [idx+1, j-1] 之间的数均大于 key
                swap(array, idx, j);
            }
        }

        // 循环之后，array[left, idx] 内均小于等于 key，array[idx+1, right] 均大于 key。
        // 并且 idx 指向最后一个小于等于 key 的值，right 还未归位，需要与 idx+1 交换一次。
        swap(array, idx + 1, right);
        // 返回第一个大于等于 key 的位置
        return idx + 1;
    }

    public static int hoarePartition(int[] array, int left, int right) {
        int key = array[right];
        int i = left;
        int j = right - 1;

        while (i <= j) {
            while (i <= j && array[i] <= key) {
                // 寻找大于 key 的值
                i++;
            }
            while (i <= j && array[j] > key) {
                // 寻找小于等于 key 的值
                j--;
            }
            swap(array, i, j);
        }

        swap(array, i, right);

        return j;
    }
}
