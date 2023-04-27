package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-11 14:44
 * 找出所有相加之和为 n 的 k 个数的组合
 * 组合中只允许含有 1~9 的正整数，并且每种组合中不存在重复的数字。
 * - 所有的数字都是正整数
 * - 解集不能包含重复的组合
 * - 示例：k=3, n=7, 输出 [[1,2,4]]
 */
public class P216Combination3 {

    public static void main(String[] args) {
        P216Combination3 p216Combination3 = new P216Combination3();
        System.out.println("p216Combination3.combination(7, 3) = " + p216Combination3.combination(8, 3));
    }

    private List<List<Integer>> data;

    private Deque<Integer> path;

    public List<List<Integer>> combination(int targetSum, int k) {
        data = new ArrayList<>();
        path = new ArrayDeque<>();
        backtrack(targetSum, k, 0, 1);
        return data;
    }

    private void backtrack(int targetSum, int k, int sum, int startIdx) {
        if (sum == targetSum && path.size() == k) {
            data.add(new ArrayList<>(path));
            return;
        }
        if (sum > targetSum) {
            // 如果当前和已经超过了目标和，停止搜索。
            return;
        }
        // 罗列所有选择
        for (int i = startIdx; i <= 9 - (k - path.size()) + 1; i++) {
            // 做出选择
            sum += i;
            path.addLast(i);
            // 回溯
            backtrack(targetSum, k, sum, i + 1);
            // 撤销选择
            sum -= i;
            path.removeLast();
        }
    }

}
