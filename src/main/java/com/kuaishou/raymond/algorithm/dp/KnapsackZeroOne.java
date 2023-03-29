package com.kuaishou.raymond.algorithm.dp;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.printMatrix;
import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.printRow;

/**
 * 背包问题
 */
public class KnapsackZeroOne {

    public static void main(String[] args) {
        int[] value = {4, 2, 3};
        int[] weight = {4, 2, 3};
        int capacity = 4;
        KnapsackZeroOne knapsack = new KnapsackZeroOne();
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

        // 基准条件：对于第 0 件物品，当背包容量可以装下第 0 件物品时，价值为第 0 件物品的价值，装不下时价值为 0。
        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = i >= weight[0] ? value[0] : 0;
        }

        // 依次选择每件物品
        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                // 当容量可以装下当前物品时，累加上当前物品的价值，否则增益为 0。
                int getI = c >= weight[i] ? dp[i - 1][c - weight[i]] + value[i] : 0;
                dp[i][c] = Math.max(dp[i - 1][c], getI);
            }
        }

        printMatrix(dp);
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

        printMatrix(dp);

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

        printRow(dp);

        return dp[capacity];
    }

}
