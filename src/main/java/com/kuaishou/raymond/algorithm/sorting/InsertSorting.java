package com.kuaishou.raymond.algorithm.sorting;

import java.util.Arrays;

public class InsertSorting {

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 8, 7, 4, 6};
         insertSorting(nums);
//        insertionSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

    }

    public static void insertSorting(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = key;
        }
    }

    public static int[] insertionSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            // 先保存要插入的值，然后寻找它需要插入的位置
            int key = array[i];
            // 将array[j] 插入到已经排好序的 array[0..i-1]中
            // 当前值的前一个值
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                // 本次循环的作用是找到属于key的位置
                // 如果key前面的值大于key的话，则交换二者的位置。
                array[j + 1] = array[j];
                j--;
            }
            // 最后将保存的key放到属于它的位置上
            array[j + 1] = key;
        }
        return array;
    }

}