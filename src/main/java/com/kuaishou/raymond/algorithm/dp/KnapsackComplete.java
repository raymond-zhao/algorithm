package com.kuaishou.raymond.algorithm.dp;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-28 17:18
 * 完全背包
 */
public class KnapsackComplete {

    public static void main(String[] args) {
        int[] value = {1, 2};
        int[] weight = {1, 2};
        int capacity = 5;
        KnapsackComplete knapsack = new KnapsackComplete();
        System.out.println(
                "knapsack.maxValueV1(value, weight, capacity) = " + knapsack.maxValueV1(value, weight, capacity));
        System.out.println(
                "knapsack.maxValueV2(value, weight, capacity) = " + knapsack.maxValueV2(value, weight, capacity));
        System.out.println(
                "knapsack.maxValueV2(value, weight, capacity) = " + knapsack.maxValueV2(value, weight, capacity));
    }

    /**
     * 空间复杂度：O(n*capacity)
     */
    public int maxValueV1(int[] value, int[] weight, int capacity) {
        int n = value.length;

        // 状态定义：dp[i][j]：在容量为 j 时，选择 0..i 之间的物品时的最大收益。
        int[][] dp = new int[n][capacity + 1];

        // 基准条件：在容量为 0..capacity 时，且只有第 0 件物品可供选择时的最大收益。
        for (int c = 0; c <= capacity; c++) {
            // 容量 c 能够装下物品 0 的数量
            int count = c / weight[0];
            dp[0][c] = value[0] * count;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                // 不选 i
                int noGet = dp[i - 1][c];
                // 选择 i：0.. c/weight[i] 件
                int getI = 0;

                for (int k = 1; ; k++) {
                    if (k * weight[i] > c) {
                        // 如果当前容量不能装下更多的第 i 件物品了，不再更新。
                        break;
                    }
                    getI = Math.max(getI, dp[i - 1][c - k * weight[i]] + k * value[i]);
                }

                dp[i][c] = Math.max(noGet, getI);
            }
        }

        return dp[n - 1][capacity];
    }

    /**
     * 空间复杂度：O(2*capacity)
     */
    public int maxValueV2(int[] value, int[] weight, int capacity) {
        int n = value.length;
        int[][] dp = new int[n][capacity + 1];

        for (int c = 0; c <= capacity; c++) {
            int count = c / weight[0];
            dp[0][c] = value[0] * count;
        }

        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int noGet = dp[(i - 1) & 1][c];
                int getI = 0;

                for (int k = 1; ; k++) {
                    if (k * weight[i] > c) {
                        break;
                    }
                    getI = Math.max(getI, dp[(i - 1) & 1][c - k * weight[i]] + value[i]);
                }

                dp[i & 1][c] = Math.max(noGet, getI);
            }
        }

        return dp[(n - 1) & 1][capacity];
    }

    /**
     * 空间复杂度：O(capacity)
     */
    public int maxValueV3(int[] value, int[] weight, int capacity) {
        int n = value.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int noGet = dp[c];
                int getI = c - weight[i] >= 0 ? dp[c - weight[i]] + value[i] : 0;
                dp[c] = Math.max(noGet, getI);
            }
        }

        return dp[capacity];
    }
}
