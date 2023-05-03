package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 10:38
 * 题目：<a href="https://leetcode.cn/problems/coin-change/?envType=study-plan-v2&id=top-100-liked">322. 零钱兑换</a>
 * 相似题目：<a href="https://leetcode.cn/problems/coin-change-ii/">518. 零钱兑换 II</a>
 */
public class Hot322CoinChange {

    public static void main(String[] args) {
        int[] coins = AlgoUtils.toIntArray("[1, 2, 5]");
        int[] coins2 = {2};
        int amount = 11;
        System.out.println("coinChange(coins, amount) = " + coinChange(coins2, 1));
    }

    /**
     * dp[i]: 组成金额 i 所需要的最少硬币数
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int money = 1; money <= amount; money++) {
            // 可供选择的硬币
            int minCoin = amount + 1;
            for (int coin : coins) {
                if (money >= coin) {
                    minCoin = Math.min(minCoin, dp[money - coin] + 1);
                }
            }
            dp[money] = minCoin;
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
