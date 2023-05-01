package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.*;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 22:39
 * 题目：<a href="https://leetcode.cn/problems/subsets/?envType=study-plan-v2&id=top-100-liked">78. 子集</a>
 * 相似题目：<a href="https://leetcode.cn/problems/subsets-ii/">90. 子集 II</a>
 */
public class Hot78Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        Hot78Subsets hot = new Hot78Subsets();
        System.out.println("hot.subsets(nums) = " + hot.subsets(nums));
        System.out.println("hot.subsetsWithDup(nums) = " + hot.subsetsWithDup(nums));
    }

    private List<List<Integer>> subsets;

    private Deque<Integer> subset;

    public List<List<Integer>> subsets(int[] nums) {
        subsets = new ArrayList<>();
        subset = new ArrayDeque<>();

        backtrack(nums, 0);

        return subsets;
    }

    private void backtrack(int[] nums, int startIdx) {
        subsets.add(new ArrayList<>(subset));
        for (int i = startIdx; i < nums.length; i++) {
            subset.addLast(nums[i]);
            backtrack(nums, i + 1);
            subset.removeLast();
        }
    }

    private boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsets = new ArrayList<>();
        subset = new ArrayDeque<>();
        used = new boolean[nums.length];

        Arrays.sort(nums);
        backtrackWithDup(nums, 0);

        return subsets;
    }

    private void backtrackWithDup(int[] nums, int startIdx) {
        subsets.add(new ArrayList<>(subset));

        for (int i = startIdx; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            subset.addLast(nums[i]);
            backtrackWithDup(nums, i + 1);
            used[i] = false;
            subset.removeLast();
        }
    }
}
