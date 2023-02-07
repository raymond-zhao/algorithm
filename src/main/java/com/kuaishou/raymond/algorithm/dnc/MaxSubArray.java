package com.kuaishou.raymond.algorithm.dnc;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class MaxSubArray {

    public static void main(String[] args) {
        // int[] nums1 = {3, -1, 2, -45, 1, 5, 7, 305, 92};
        int[] nums2 = {3, 4, -10, 5, -6};
        int[] array = nums2;
        log.info("最大子数组 = {}", maxSubArray(array, 0, array.length - 1));
        log.info("最大子数组 = {}", iterativeMaxSubArray(array));
    }

    public static int[] iterativeMaxSubArray(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return array;
        }

        int sum = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int low = 0;
        int high = 0;
        int currentLow = 0;
        int currentHigh;

        for (int i = 0; i < array.length; i++) {
            currentHigh = i;
            if (sum <= 0) {
                sum = array[i];
                currentLow = i;
            } else {
                sum += array[i];
            }

            if (sum > max) {
                max = sum;
                low = currentLow;
                high = currentHigh;
            }
        }

        return new int[]{low, high, max};
    }

    public static int[] maxSubArray(int[] array, int left, int right) {
        if (left == right) {
            return new int[]{left, right, array[left]};
        }

        if (left < right) {
            int middle = (left + right) >>> 1;
            int[] leftSub = maxSubArray(array, left, middle);
            int[] rightSub = maxSubArray(array, middle + 1, right);
            int[] crossingSub = maxCrossingArray(array, left, middle, right);

            if (leftSub[2] > rightSub[2] && leftSub[2] > crossingSub[2]) {
                return leftSub;
            } else if (rightSub[2] > leftSub[2] && rightSub[2] > crossingSub[2]) {
                return rightSub;
            } else {
                return crossingSub;
            }
        }
        return null;
    }

    private static int[] maxCrossingArray(int[] array, int left, int middle, int right) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int idxOfLeftSum = middle;
        int idxOfRightSum = middle + 1;

        int sum = 0;

        for (int i = middle; i >= left; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                idxOfLeftSum = i;
            }
        }

        sum = 0;
        for (int j = middle + 1; j <= right; j++) {
            sum += array[j];
            if (sum > rightSum) {
                rightSum = sum;
                idxOfRightSum = j;
            }
        }

        return new int[]{idxOfLeftSum, idxOfRightSum, leftSum + rightSum};
    }
}
