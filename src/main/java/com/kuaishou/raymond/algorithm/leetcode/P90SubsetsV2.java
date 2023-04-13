package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个数组 nums，数组中可能包含重复元素，返回这个数组的所有子集。
 * 子集不能重复
 */
public class P90SubsetsV2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        P90SubsetsV2 p90 = new P90SubsetsV2();
        System.out.println("p90.subsetsWithDup(nums) = " + p90.subsetsWithDup(nums));
    }

    private List<List<Integer>> data;

    private Deque<Integer> subset;

    private boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        data = new ArrayList<>();
        subset = new ArrayDeque<>();
        used = new boolean[nums.length];

        Arrays.sort(nums);
        backtrack(nums, 0);

        return data;
    }

    private void backtrack(int[] nums, int startIdx) {
        // for 循环中 i > nums.length 已经不会再继续递归，所以可以不加终止条件。
        data.add(new ArrayList<>(subset));
        // 罗列所有选择
        for (int i = startIdx; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做出选择
            subset.addLast(nums[i]);
            used[i] = true;
            // 回溯
            backtrack(nums, i + 1);
            // 撤销选择
            subset.removeLast();
            used[i] = false;
        }
    }
}
