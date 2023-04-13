package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 10:42
 * 给定一个整数数组 nums，返回它的全排列。
 */
public class P47Permutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        P47Permutation p47 = new P47Permutation();
        System.out.println("p47.permute(nums) = " + p47.permute(nums));
    }

    private List<List<Integer>> data;

    private Deque<Integer> permutation;

    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        data = new ArrayList<>();
        permutation = new ArrayDeque<>();
        used = new boolean[nums.length];

        backtrack(nums);

        return data;
    }

    private void backtrack(int[] nums) {
        if (permutation.size() == nums.length) {
            data.add(new ArrayList<>(permutation));
            return;
        }
        // 罗列所有选择：因为排列需要使用所有数字，所以要从 0 开始遍历。
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 做出选择
            permutation.addLast(nums[i]);
            used[i] = true;
            // 回溯
            backtrack(nums);
            // 撤销选择
            permutation.removeLast();
            used[i] = false;
        }
    }
}
