package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.*;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 22:28
 * 题目：<a href="https://leetcode.cn/problems/permutations/?envType=study-plan-v2&id=top-100-liked">46. 全排列</a>
 * 相似题目：<a href="https://leetcode.cn/problems/permutations-ii/">47. 全排列 II</a>
 */
public class Hot46Permutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Hot46Permutations hot = new Hot46Permutations();
        System.out.println("hot.permute(nums) = " + hot.permute(nums));
    }

    private List<List<Integer>> permutations;

    private Deque<Integer> permutation;

    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        permutations = new ArrayList<>();
        permutation = new ArrayDeque<>();
        used = new boolean[nums.length];

        backtrack(nums);

        return permutations;
    }

    private void backtrack(int[] nums) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        // 罗列所有选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            permutation.addLast(nums[i]);
            used[i] = true;
            backtrack(nums);
            used[i] = false;
            permutation.removeLast();
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        permutations = new ArrayList<>();
        permutation = new ArrayDeque<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrackUnique(nums);
        return permutations;
    }

    private void backtrackUnique(int[] nums) {
        if (permutation.size() == nums.length) {
            // 如果已经访问到数组最后一个元素，认为已经寻找到了一个排列。
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        // 罗列所有选择
        for (int idx = 0; idx < nums.length; idx++) {
            if (idx > 0 && nums[idx] == nums[idx - 1] && !used[idx - 1]) {
                continue;
            }
            if (used[idx]) {
                continue;
            }
            // 做出选择
            permutation.addLast(nums[idx]);
            used[idx] = true;
            // 回溯
            backtrackUnique(nums);
            // 撤销选择
            permutation.removeLast();
            used[idx] = false;
        }
    }
}
