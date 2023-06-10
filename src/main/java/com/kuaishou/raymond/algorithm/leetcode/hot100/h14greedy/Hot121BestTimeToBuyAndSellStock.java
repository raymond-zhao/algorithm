package com.kuaishou.raymond.algorithm.leetcode.hot100.h14greedy;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 23:06
 * 题目：<a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&id=top-100-liked">121. 买卖股票的最佳时机</a>
 * 解析：<a href="https://raymond-zhao.top/campus-interview/#/LeetCode/DP/BuyAndSellStock">股票买卖问题</a>
 */
public class Hot121BestTimeToBuyAndSellStock {

    /**
     * 在只能交易一次的情况下，买卖股票可以获得的最大收益。
     * 动态规划
     */
    public int maxProfitDP(int[] prices) {
        // dp[i][j]: 在只能交易一次的情况下，第 i 天结束后，持有 j 支股票的收益。
        int[][] dp = new int[prices.length][2];
        // 第 0 天结束，持有0份股票的收益。
        dp[0][0] = 0;
        // 第 0 天结束，持有1份股票的收益。
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 隐藏条件：dp[i-1][0]=0
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * 在只能交易一次的情况下，买卖股票可以获得的最大收益。
     * 贪心算法
     */
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            cost = Math.min(price, cost);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

}
