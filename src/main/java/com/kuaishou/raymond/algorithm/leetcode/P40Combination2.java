package com.kuaishou.raymond.algorithm.leetcode;

import java.util.*;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-11 14:44
 * <a href="https://leetcode.cn/problems/combination-sum-ii/">...</a>
 * 40. 组合总和 II
 */
public class P40Combination2 {

    public static void main(String[] args) {
        P40Combination2 p40 = new P40Combination2();
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        System.out.println("p39.combination(candidates, target) = " + p40.combination(candidates, target));
    }

    private List<List<Integer>> data;

    private Deque<Integer> path;

    private boolean[] used;

    public List<List<Integer>> combination(int[] candidates, int target) {
        data = new ArrayList<>();
        path = new ArrayDeque<>();
        used = new boolean[candidates.length];

        Arrays.sort(candidates);
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
            // 去重
            if (i > idx && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做出选择
            sum += candidates[i];
            path.addLast(candidates[i]);
            used[i] = true;
            // 回溯
            backtrack(candidates, target, sum, i + 1);
            // 撤销选择
            sum -= candidates[i];
            path.removeLast();
            used[i] = false;
        }
    }

}
