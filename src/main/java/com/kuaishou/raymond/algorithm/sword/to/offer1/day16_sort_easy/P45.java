package com.kuaishou.raymond.algorithm.sword.to.offer1.day16_sort_easy;

import java.util.Arrays;

/**
 * 把数组中的数字组合起来排成最小的数字
 * 如 [10, 2] => 102; [3, 30, 34, 5, 9] => 30 3 34 5 9
 */
public class P45 {

    public static void main(String[] args) {
        int[] nums1 = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        P45 p45 = new P45();

        p45.quickSort(nums2, 0, nums2.length - 1);
        System.out.println("Arrays.toString(nums2) = " + Arrays.toString(nums2));
//        System.out.println("p45.minNumber(nums1) = " + p45.minNumber(nums1));
//        System.out.println("p45.minNumber(nums2) = " + p45.minNumber(nums2));
//        System.out.println("p45.minNumber2(nums2) = " + p45.minNumber2(nums2));
    }

    public String minNumber2(int[] nums) {
        String[] strArray = new String[nums.length];
        int i = 0;
        for (int num : nums) {
            // 把数字转成字符串
            strArray[i++] = String.valueOf(num);
        }

        // 指定规则对字符串排序
        Arrays.sort(strArray, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            // 直接拼接
            builder.append(s);
        }
        return builder.toString();
    }

    /**
     * 将数组中的数字看做字符串，比较规则如下：
     * 如果 x + y > y + x, 则 x > y; 如 3 + 30 = 330 > 30 + 3 = 303，则 3 > 30;
     * 如果 x + y < y + x, 则 x < y; 如 30 + 34 = 3034 < 34 + 30 = 3430，则 30 < 34;
     * 使用任意一种排序方法，排序后的数组相连即为最小的数字。
     */
    public String minNumber(int[] nums) {
        StringBuilder builder = new StringBuilder();
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            builder.append(num);
        }
        return builder.toString();
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partition = partition(nums, left, right);
            quickSort(nums, left, partition - 1);
            quickSort(nums, partition + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right]; // 选取数组最右侧元素为基准元素
        int index = left - 1; // index 及其左侧元素均小于等于 pivot

        for (int i = left; i < right; i++) {
            if (leq(nums[i], pivot)) {
                ++index;
                swap(nums, index, i);
            }
        }
        swap(nums, index + 1, right);
        return index + 1;
    }

    private boolean leq(int x, int y) {
        String xPlusY = String.valueOf(x) + y;
        String yPlusX = String.valueOf(y) + x;
        return xPlusY.compareTo(yPlusX) < 1;
    }

    /**
     * 比较两个字符串 x 代表的数字是否小于 y 代表的数字。
     * 如 x=303, y=330, 则 x < y;
     */
    private static boolean strLEQ(String x, String y) {
        return x.compareTo(y) < 1;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
