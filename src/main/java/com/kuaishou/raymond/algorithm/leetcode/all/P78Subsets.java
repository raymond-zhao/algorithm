package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P78Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        P78Subsets p78 = new P78Subsets();
        System.out.println("p78.subsets(nums) = " + p78.subsets(nums));
    }

    private List<List<Integer>> data;

    private Deque<Integer> set;

    public List<List<Integer>> subsets(int[] nums) {
        data = new ArrayList<>();
        set = new ArrayDeque<>();

        backtrack(nums, 0);

        return data;
    }

    private void backtrack(int[] nums, int startIdx) {
        // 记录遍历过程中的所有结点
        data.add(new ArrayList<>(set));
        // 罗列所有选择
        for (int i = startIdx; i < nums.length; i++) {
            set.addLast(nums[i]);
            backtrack(nums, i + 1);
            set.removeLast();
        }
    }

}
