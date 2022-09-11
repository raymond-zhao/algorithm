package com.kuaishou.raymond.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Slf4j
public class Sorting {

    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        // log.info("InsertionSort = {}", insertionSort(array));
        // log.info("BubbleSort = {}", bubbleSort(array));
        // log.info("MergeSort = {}", mergeSort(array, 0, array.length - 1));
        log.info("CountInversions = {}", countInversions(array, 0, array.length - 1));
    }

    public static int[] insertionSort(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return array;
        }

        for (int i = 1; i < array.length; i++) {
            int currentNum = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > currentNum) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentNum;
        }

        return array;
    }

    public static int[] bubbleSort(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    exchange(array, j, j - 1);
                }
            }
        }

        return array;
    }

    private static void exchange(int[] array, int j, int i) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Nullable
    public static int[] mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) >>> 1;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            return merge(array, left, middle, right);
        }
        return null;
    }

    private static int[] merge(int[] array, int left, int middle, int right) {
        int n_1 = middle - left + 1;
        int n_2 = right - middle;

        int[] leftArray = new int[n_1 + 1];
        int[] rightArray = new int[n_2 + 1];

        if (n_1 >= 0) {
            System.arraycopy(array, left, leftArray, 0, n_1);
        }

        for (int j = 0; j < n_2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        leftArray[n_1] = Integer.MAX_VALUE;
        rightArray[n_2] = Integer.MAX_VALUE;

        int i = 0, j = 0;

        for (int k = left; k <= right; k++) {
            array[k] = leftArray[i] <= rightArray[j] ? leftArray[i++] : rightArray[j++];
        }

        return array;
    }

    public static int countInversions(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) >>> 1;
            int lInversions = countInversions(array, left, middle);
            int rInversions = countInversions(array, middle + 1, right);
            return mergeCount(array, left, middle, right) + lInversions + rInversions;
        }
        return 0;
    }

    private static int mergeCount(int[] array, int left, int middle, int right) {
        int lenOfL = middle - left + 1;
        int lenOfR = right - middle;

        int[] lArray = new int[lenOfL + 1];
        int[] rArray = new int[lenOfR + 1];

        if (lenOfL >= 0) {
            System.arraycopy(array, left, lArray, 0, lenOfL);
        }

        for (int j = 0; j < lenOfR; j++) {
            rArray[j] = array[middle + 1 + j];
        }

        lArray[lenOfL] = Integer.MAX_VALUE;
        rArray[lenOfR] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int inversions = 0;

        for (int k = left; k <= right; k++) {
            if (lArray[i] <= rArray[j]) {
                array[k] = lArray[i++];
            } else {
                inversions += lenOfL - i;
                array[k] = rArray[j++];
            }
        }

        return inversions;
    }

}
