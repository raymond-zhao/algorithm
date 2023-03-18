package com.kuaishou.raymond.algorithm.swordtooffer.day29_dp_hard_star;

import java.util.Arrays;

/**
 * n 个骰子的点数
 * n \in [1, 11]
 */
public class P60 {

    public static void main(String[] args) {
        P60 p60 = new P60();
        System.out.println("Arrays.toString(p60.dicesProbability(2)) = " + Arrays.toString(p60.dicesProbability(1)));
    }

    /**
     * - 状态定义
     *  - dp[i][j]：由 i 个骰子可以组成点数 j 的次数
     *  - 每个骰子（6面）有 6 种不同的点数，所以 dp[i][j] 可以由 6 个状态转换而来。
     * - 状态转移（LaTex 形式）
     * dp[i][j] = \sum_{k=1}^{6}\frac{dp[i-1][j-k]}{6}
     */
    public double[] dicesProbability(int n) {
        // 根据状态定义可知，不能有 0 个骰子，也不会有点数为 0 的情况，所以，声明 dp 数组时需要包含并处理这种情况。
        // dp[i][j] 使用 i 个骰子时可以组成点数 j 的次数
        double[][] dp = new double[n + 1][6 * n + 1];
        // 初始化 1 个骰子时的情况
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        // 递推存在 2 个或更多骰子的情况
        for (int i = 2; i <= n; i++) { // 骰子的个数
            for (int j = i; j <= 6 * i; j++) { // 组成的点数
                for (int k = 1; k <= Math.min(6, j); k++) { // j-k 需要判断是否越界
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        double[] data = new double[5 * n  + 1];
        for (int i = n; i <= 6 * n; i++) {
            // 点数从 1 点枚举到 6n 点
            data[i - n] = dp[n][i] / Math.pow(6, n);
        }

        return data;
    }

    /**
     * dp[i][j]：由 i 个骰子可以组成点数 j 的概率
     */
    public double[] dicesProbabilityV2(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);

        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            dp = temp;
        }

        return dp;
    }

}
