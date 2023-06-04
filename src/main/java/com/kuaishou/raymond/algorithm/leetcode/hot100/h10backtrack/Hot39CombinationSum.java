package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 23:23
 * 题目：<a href="https://leetcode.cn/problems/combination-sum/?envType=study-plan-v2&id=top-100-liked">39. 组合总和</a>
 * 相似题目：
 * - <a href="https://leetcode.cn/problems/combination-sum-ii/">40. 组合总和 II</a>
 * - <a href="https://leetcode.cn/problems/combination-sum-iii/">216. 组合总和 III</a>
 * - <a href="https://leetcode.cn/problems/combination-sum-iv/">377. 组合总和 Ⅳ</a>
 */
public class Hot39CombinationSum {

    public static void main(String[] args) {
        int[] candidates = AlgoUtils.toIntArray("[2,3,6,7]");
        int target = 7;
        Hot39CombinationSum hot39 = new Hot39CombinationSum();
        System.out.println("hot39.combinationSum(candidates, target) = " + hot39.combinationSum(candidates, target));
    }

    private List<List<Integer>> combinations;

    private Deque<Integer> combination;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinations = new ArrayList<>();
        combination = new ArrayDeque<>();

        backtrack(candidates, target, 0, 0);

        return combinations;
    }

    private void backtrack(int[] candidates, int targetSum, int currentSum, int startIdx) {
        if (currentSum == targetSum) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        if (currentSum > targetSum) {
            return;
        }
        for (int i = startIdx; i < candidates.length; i++) {
            currentSum += candidates[i];
            combination.addLast(candidates[i]);
            backtrack(candidates, targetSum, currentSum, i);
            currentSum -= candidates[i];
            combination.removeLast();
        }
    }

}
