package com.kuaishou.raymond.algorithm.swordtooffer.day8_dp_easy;

/**
 * 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次，可获得的最大利润是多少。
 */
public class P63 {
    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4};
        P63 p63 = new P63();
        System.out.println("p63.maxProfits(nums) = " + p63.maxProfits(nums)); // 5
        System.out.println("p63.maxProfits2(nums) = " + p63.maxProfits2(nums)); // 5
        System.out.println("p63.maxProfits3(nums) = " + p63.maxProfits3(nums));
    }

    /**
     * 暴力求解：O(n^2)
     */
    public int maxProfits(int[] prices) {
        int maxProfits = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfits = Math.max(maxProfits, profit);
            }
        }
        return maxProfits;
    }

    /**
     * 动态规划：
     * 时间：O(n)，空间：O(n)
     */
    public int maxProfits2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp[i] 到第 i 日为止，可获得的最大利润。
        // dp[i] = max(dp[i-1], prices[i] - min(prices[0:i]))
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[prices.length - 1];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfits3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

}
