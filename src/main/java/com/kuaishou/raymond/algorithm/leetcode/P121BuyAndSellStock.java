package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-21 10:30
 */
public class P121BuyAndSellStock {

    public static void main(String[] args) {
        P121BuyAndSellStock p121BuyAndSellStock = new P121BuyAndSellStock();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int maxProfit = p121BuyAndSellStock.maxProfit1Trade(prices1);
        System.out.println("maxProfit = " + maxProfit);
    }

    /**
     * LeetCode121: 买卖股票的最佳时机（一次交易）
     */
    public int maxProfit1Trade(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp[i][0 | 1]: 第 i 天结束时，持有 0|1 份股票时可以获取的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0; // 第 0 天结束时持有 0 份股票，即不买入，保持现状。
        dp[0][1] = -prices[0]; // 第 i 天结束时持有 1 份股票，即买入股票，总收益=支出购买股票的钱。

        for (int i = 1; i < prices.length; i++) {
            // 休息或卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 休息或买入（前一天持有 0 份股票，则在此之前从未买入过股票，收益为 0）
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    /**
     * 买卖股票的最佳时机（不限制交易次数）
     */
    public int maxProfitNTrade(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 休息/卖出
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 休息/买入
        }

        return dp[length - 1][0];
    }
}
