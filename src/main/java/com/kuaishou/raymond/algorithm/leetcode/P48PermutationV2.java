package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 10:49
 */
public class P48PermutationV2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        P48PermutationV2 p48 = new P48PermutationV2();
        System.out.println("p48.permute(nums) = " + p48.permute(nums));
    }

    private List<List<Integer>> data;

    private Deque<Integer> permutation;

    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        data = new ArrayList<>();
        permutation = new ArrayDeque<>();
        used = new boolean[nums.length];

        Arrays.sort(nums);
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
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
}
