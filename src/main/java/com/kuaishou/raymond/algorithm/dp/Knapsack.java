package com.kuaishou.raymond.algorithm.dp;

/**
 * 背包问题
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] value = {4, 2, 3};
        int[] weight = {4, 2, 3};
        int capacity = 5;
        Knapsack knapsack = new Knapsack();
        System.out.println("knapsack.maxValueV1(value, weight, capacity) = " + knapsack.maxValueV1(value, weight, capacity));
        System.out.println("knapsack.maxValueV2(value, weight, capacity) = " + knapsack.maxValueV2(value, weight, capacity));
        System.out.println("knapsack.maxValueV3(value, weight, capacity) = " + knapsack.maxValueV3(value, weight, capacity));
    }

    /**
     * 空间复杂度: O(n*capacity)
     */
    public int maxValueV1(int[] value, int[] weight, int capacity) {
        int n = value.length;

        // dp[i][j]: 在背包容量为 j 时，在选择到物品 i 时持有的最大收益。
        int[][] dp = new int[n][capacity + 1];

        // 基准条件
        for (int i = 0; i < capacity + 1; i++) {
            dp[0][i] = i >= weight[0] ? value[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int getI = c >= weight[i] ? dp[i - 1][c - weight[i]] + value[i] : 0;
                dp[i][c] = Math.max(dp[i - 1][c], getI);
            }
        }

        return dp[n - 1][capacity];
    }

    /**
     * 空间复杂度：O(2*capacity)
     */
    public int maxValueV2(int[] value, int[] weight, int capacity) {
        int n = value.length;

        // dp[i][j]: 在背包容量为 j 时，在选择到物品 i 时持有的最大收益。
        int[][] dp = new int[2][capacity + 1];

        // 基准条件
        for (int i = 0; i < capacity + 1; i++) {
            dp[0][i] = i >= weight[0] ? value[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int getI = c >= weight[i] ? dp[(i - 1) & 1][c - weight[i]] + value[i] : 0;
                dp[i & 1][c] = Math.max(dp[(i - 1) & 1][c], getI);
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
            for (int c = capacity; c >= weight[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - weight[i]] + value[i]);
            }
        }
        return dp[capacity];
    }
}
