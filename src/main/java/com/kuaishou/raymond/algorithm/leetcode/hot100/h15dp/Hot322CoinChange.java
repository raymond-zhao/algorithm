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
     * 完全背包问题
     * 时间复杂度：O(amount * coin_size)
     * @param coins 可供选择的硬币
     * @param amount 目标金额
     * @return 组成 amount 所需的最少硬币数
     */
    public static int coinChange(int[] coins, int amount) {
        // 状态定义：dp[i] 表示组成金额 i 所需要的最少硬币数
        int[] dp = new int[amount + 1];
        // 基准条件：组成金额 0 所需要的最少硬币个数为 0 枚
        dp[0] = 0;
        // 状态转移
        for (int money = 1; money <= amount; money++) {
            // 外层循环表示固定金额
            dp[money] = amount + 1;
            for (int coin : coins) {
                // 内层循环表示使用面值为 coin 的硬币组成金额 money 所需要的最少硬币数，
                // 在面对硬币 coin 时，有两种选择，一种是「选择硬币」，一种是「不选择硬币」。
                // 当不选择当前硬币时，表示使用之前已出现过的硬币的组合结果。
                // 当选择当前硬币时，硬币数+1，但要依赖于组成 money-coin 的值。
                if (money >= coin) {
                    dp[money] = Math.min(dp[money], dp[money - coin] + 1);
                }
            }
        }
        // 返回结果
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 零钱兑换II：每一枚面值的硬币可以选取多次
     * @param amount 目标金额
     * @param coins 可供选择的硬币面值
     * @return 组成金额 amount 所需要的最少硬币数
     * <a href="https://leetcode.cn/problems/coin-change-ii/solution/518-ling-qian-dui-huan-ii-by-stormsunshi-6e4d/">题解</a>
     * 需要首先遍历每种硬币然后遍历每种金额
     */
    public static int change(int amount, int[] coins) {
        int n = coins.length;
        // 状态定义：dp[i][j] 表示在使用前 i 枚硬币的条件下，组成金额 i 所需要的最少硬币数。
        int[][] dp = new int[n + 1][amount + 1];
        // 基准条件：在不使用任何硬币的情况下，组成金额 0 的情况只有 1 种，那就是不选择任何硬币。
        dp[0][0] = 1;
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int money = 0; money <= amount; money++) {
                int maxCoinCount = money / coins[i - 1];
                for (int k = 0; k <= maxCoinCount; k++) {
                    // 面对每枚硬币的选择：选 0,1,2,... 个
                    dp[i][money] += dp[i - 1][money - coins[i - 1] * k];
                }
            }
        }
        // 返回结果
        return dp[n][amount];
    }
}
