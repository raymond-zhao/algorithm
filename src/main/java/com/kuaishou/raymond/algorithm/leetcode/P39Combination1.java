package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-11 14:44
 * 给定一个无重复元素的数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。
 * - candidates 中的所有数字可以重复选取
 * - 所有数字都是正整数
 * - 解集不能包含重复的组合
 * - Input: [2, 3, 6, 7], target = 7.
 * - Output: [[7], [2, 2, 3]]
 */
public class P39Combination1 {

    public static void main(String[] args) {
        P39Combination1 p39 = new P39Combination1();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("p39.combination(candidates, target) = " + p39.combination(candidates, target));
    }

    private List<List<Integer>> data;

    private Deque<Integer> path;

    public List<List<Integer>> combination(int[] candidates, int target) {
        data = new ArrayList<>();
        path = new ArrayDeque<>();

        backtrack(candidates, target, 0, 0);

        return data;
    }

    private void backtrack(int[] candidates, int target, int sum, int idx) {
        if (sum == target) {
            data.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        // 罗列所有选择
        for (int i = idx; i < candidates.length; i++) {
            // 做出选择
            sum += candidates[i];
            path.addLast(candidates[i]);
            // 回溯
            backtrack(candidates, target, sum, i);
            // 撤销选择
            sum -= candidates[i];
            path.removeLast();
        }
    }
}
