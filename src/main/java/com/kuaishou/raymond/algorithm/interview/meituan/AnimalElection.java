package com.kuaishou.raymond.algorithm.interview.meituan;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-05-29 11:48
 * 小动物选举
 * 在一个森林里有一群小动物，它们打算选举出一个动物首领。
 * 已知每个小动物的编号分别为 0、1、2 ... n，它们的能力值依次为为从大到小
 * 即 0 号小动物能力值最大，n 号小动物能力值最小。
 * 已知在小动物中存在崇拜现象，但是小动物只会崇拜能力值比自己大的动物。
 * 在投票的过程中，已知小动物要么投票给自己，要么不投票，或者投票的对象为它所崇拜的小动物。
 * 问每只小动物能够获得的最高票数为多少?
 */
public class AnimalElection {

    public static int[] calculateHighestVotes(int n) {
        // 1. 状态定义：dp[i] 表示每个小动物能获得的最大投票数
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // 每个小动物最多至少可以获得 1 票
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (j > dp[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp;
    }

}
