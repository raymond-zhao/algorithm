package com.kuaishou.raymond.algorithm.swordtooffer.day17_sort_medium;

import java.util.Arrays;

/**
 * P40：最小的 k 个数
 * 输入整数数组 arr，找出其中最小的 k 个数。
 * 例如，输入数组 4，5，1，6，2，7，3，8 这 8 个数字，则最小的 4 个数字是 1，2，3，4.
 */
public class P40 {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1}; // 2, res=[1,2] 或 [2,1]
        int[] nums2 = {0, 1, 2, 1}; // 1, res=[0]
        P40 p40 = new P40();
        System.out.println(
                "Arrays.toString(p40.getLeastNumbers(nums1, 2)) = " + Arrays.toString(p40.getLeastNumbers(nums1, 2)));
        System.out.println(
                "Arrays.toString(p40.getLeastNumbers(nums2, 1)) = " + Arrays.toString(p40.getLeastNumbers(nums2, 1)));

        System.out.println(
                "Arrays.toString(p40.getLeastNumber2(nums1, 2)) = " + Arrays.toString(p40.getLeastNumber2(nums1, 2)));
        System.out.println(
                "Arrays.toString(p40.getLeastNumber2(nums2, 1)) = " + Arrays.toString(p40.getLeastNumber2(nums2, 1)));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return new int[0];
        }
        Arrays.sort(arr);
        int[] data = new int[k];
        System.arraycopy(arr, 0, data, 0, k);
        return data;
    }

    /**
     * 求数组中最小的 k 个数，并没有要求完全有序，可以利用快排中的 partition 索引进行短路操作节省排序次数。
     * 核心思想：当 index=partition=k 时，停止排序，返回此时数组中 0~(k-1) 的数字，不区分顺序。
     */
    public int[] getLeastNumber2(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return new int[0];
        }

        quickSortMutation(arr, 0, arr.length - 1, k);
        int[] data = new int[k];
        System.arraycopy(arr, 0, data, 0, k);
        return data;
    }

    public void quickSortMutation(int[] arr, int left, int right, int k) {
        if (left < right) {
            int partition = partition(arr, left, right);
            if (partition == k) {
                return;
            }
            quickSortMutation(arr, left, partition - 1, k);
            quickSortMutation(arr, partition + 1, right, k);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int key = arr[right];
        int index = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] <= key) {
                index++;
                swap(arr, index, i);
            }
        }
        swap(arr, index + 1, right);
        return index + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
