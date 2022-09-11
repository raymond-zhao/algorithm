package com.kuaishou.raymond.algorithm.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class LIS {

    public static void main(String[] args) {
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18};
        // log.info("res =  {}", lengthOfLIS(array));
        // [1, 1, 1, 2, 2, 3, 4, 4]
        log.info("LIS DP 数组 = {}", getDPOfLIS(array));
        log.info("最长上升子序列 = {}", getLIS(array));
    }

    public static int lengthOfLIS(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return 0;
        }
        // dp[i] 表示到下标 i 为止，最长子序列的长度。
        // 循环不变量：最长递增子序列的尾元素，一定是这个数组/子序列中最大的元素。
        int[] dp = new int[array.length];
        int longest = 1;

        dp[0] = 1; // 最大长度至少为当前字符

        for (int i = 1; i < array.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }

    /**
     * 获取最长递增子序列 DP 数组
     */
    public static int[] getDPOfLIS(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return new int[0];
        }
        int length = array.length;
        int[] dp = new int[length];
        // dp[i] 为到达 i 时最大子序列的长度
        dp[0] = 1;

        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 获取最长子序列
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public static int[] getLIS(int[] array) {
        int[] dpOfLIS = getDPOfLIS(array);

        // dp 数组中的最大值
        int maxOfDP = dpOfLIS[0];
        // dp 数组中的最大值在 array 数组中的索引
        int indexOfMaxOfArray = 0;

        // 寻找 DP 数组中的最大值，这个值等于 LIS 的长度，
        // 这个值的索引等于这个最大值在 array 中的位置，
        // 并且这个最大值是 LIS 的尾元素。
        for (int i = 1; i < dpOfLIS.length; i++) {
            if (dpOfLIS[i] > maxOfDP) {
                maxOfDP = dpOfLIS[i];
                indexOfMaxOfArray = i;
            }
        }

        // DP 数组中的最大值即为最长上升子序列的长度
        int[] res = new int[maxOfDP];
        res[--maxOfDP] = array[indexOfMaxOfArray];
        // 根据 DP 数组与 Array 数组寻找 LIS

        for (int i = indexOfMaxOfArray; i >= 0; i--) {
            // 这个过程得到的结果刚好为字典序
            if (array[i] < array[indexOfMaxOfArray] && dpOfLIS[i] == dpOfLIS[indexOfMaxOfArray] - 1) {
                res[--maxOfDP] = array[i];
                indexOfMaxOfArray = i;
            }
        }

        return res;
    }
}
