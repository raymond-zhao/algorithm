package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-06 16:54
 * P46：全排列
 * 给定一个不含重复字符的数组 nums，输出这个数组中的数字的全排列，不限顺序。
 */
public class P46Permutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long start = System.currentTimeMillis();
        System.out.println("permute(nums) = " + permute(nums));
        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));
    }

    private static List<List<Integer>> data;

    private static boolean[] visited;

    public static List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        data = new ArrayList<>();
        visited = new boolean[length];

        backtrack(nums, new ArrayList<>());

        return data;
    }

    private static void backtrack(int[] nums, List<Integer> permutation) {
        if (permutation.size() == nums.length) {
            data.add(new ArrayList<>(permutation));
            return;
        }

        // 站在历史的选择点，罗列所有的选择。
        for (int idx = 0; idx < nums.length; idx++) {
            if (visited[idx]) {
                // 如果已经使用过
                continue;
            }
            // 做出选择
            visited[idx] = true;
            permutation.add(nums[idx]);
            // 回溯
            backtrack(nums, permutation);
            // 撤销选择
            permutation.remove(permutation.size() - 1);
            visited[idx] = false;
        }
    }
}
