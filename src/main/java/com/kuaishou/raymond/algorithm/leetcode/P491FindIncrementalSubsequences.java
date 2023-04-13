package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 19:52
 * 寻找数组中长度至少为 2 的递增子序列
 */
public class P491FindIncrementalSubsequences {

    public static void main(String[] args) {
        P491FindIncrementalSubsequences p491 = new P491FindIncrementalSubsequences();
        int[] nums = {4, 7, 6, 7};
        System.out.println("p491.findSubsequences(nums) = " + p491.findSubsequences(nums));
    }

    private List<List<Integer>> data;

    private Deque<Integer> subsequence;

    public List<List<Integer>> findSubsequences(int[] nums) {
        data = new ArrayList<>();
        subsequence = new ArrayDeque<>();
        backtrackV2(nums, 0);
        return data;
    }

    private void backtrack(int[] nums, int startIdx) {
        if (subsequence.size() >= 2) {
            // 添加之后不要 return，需要访问每一个节点。
            data.add(new ArrayList<>(subsequence));
        }
        // 当前层去重
        int[] set = new int[201];
        // 罗列所有选择
        for (int i = startIdx; i < nums.length; i++) {
            // 判断是否满足递增条件
            // 判断当前层之前是否使用过当前数字
            if (!subsequence.isEmpty() && subsequence.peekLast() > nums[i] || set[nums[i] + 100] == 1) {
                continue;
            }
            // 做访问标记
            set[nums[i] + 100] = 1;
            // 做出选择
            subsequence.addLast(nums[i]);
            // 回溯
            backtrack(nums, i + 1);
            // 撤销选择
            subsequence.removeLast();
        }
    }

    private void backtrackV2(int[] nums, int startIdx) {
        if (subsequence.size() >= 2) {
            // 添加之后不要 return，需要访问每一个节点。
            data.add(new ArrayList<>(subsequence));
        }
        // 当前层去重
        Map<Integer, Integer> map = new HashMap<>();
        // 罗列所有选择
        for (int i = startIdx; i < nums.length; i++) {
            // 判断是否满足递增条件
            // 判断当前层之前是否使用过当前数字
            if (!subsequence.isEmpty() && subsequence.peekLast() > nums[i] || map.getOrDefault(nums[i], 0) == 1) {
                continue;
            }
            // 做访问标记
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            // 做出选择
            subsequence.addLast(nums[i]);
            // 回溯
            backtrackV2(nums, i + 1);
            // 撤销选择
            subsequence.removeLast();
        }
    }

}
