package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-11 10:33
 * LC: 77 数字组合
 * 返回 [1, n] 中所有可能的 k 个数字的组合
 */
public class P77Combination {

    public static void main(String[] args) {
        P77Combination p77Combination = new P77Combination();
        System.out.println("p77Combination.combine(4, 2) = " + p77Combination.combine(4, 2));
    }

    private List<List<Integer>> data;

    private Deque<Integer> path;

    public List<List<Integer>> combine(int n, int k) {
        data = new ArrayList<>();
        path = new ArrayDeque<>();

        // backtrack(n, k, 1);
        backtrackCuttingEdge(n, k, 1);

        return data;
    }

    private void backtrack(int n, int k, int startIdx) {
        if (path.size() == k) {
            data.add(new ArrayList<>(path));
            return;
        }
        // 罗列所有选择
        for (int i = startIdx; i <= n; i++) {
            path.addLast(i);
            backtrack(n, k, i + 1);
            path.removeLast();
        }
    }

    private void backtrackCuttingEdge(int n, int k, int startIdx) {
        if (path.size() == k) {
            data.add(new ArrayList<>(path));
            return;
        }
        // 剪枝：当[1, n]区间中还未使用的元素个数大于组成 K 个元素还需要的个数时才回溯
        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            backtrackCuttingEdge(n, k, i + 1);
            path.removeLast();
        }
    }
}
