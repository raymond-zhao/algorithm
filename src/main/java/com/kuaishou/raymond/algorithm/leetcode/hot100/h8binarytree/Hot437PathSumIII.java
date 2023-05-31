package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 23:57
 * 题目：<a href="https://leetcode.cn/problems/path-sum-iii/?envType=study-plan-v2&id=top-100-liked">437. 路径总和 III</a>
 * 相似题目：
 * - <a href="https://leetcode.cn/problems/path-sum/">112. 路径总和</a>
 * - <a href="https://leetcode.cn/problems/path-sum-ii/">113. 路径总和 II</a>
 * - <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&id=top-100-liked">124. 二叉树中的最大路径和</a>
 * - 前缀和
 */
public class Hot437PathSumIII {

    private final Map<Long, Integer> prefix = new HashMap<>();

    /**
     * 前缀和：从根节点到当前节点时所有路径的和
     * 先序遍历二叉树，记录下从根节点root到当前节点p的路径上除当前节点以外所有节点的前缀和，
     * 在已保存的前缀和中查找是否存在前缀和刚好等于当前节点到根节点root的前缀和currentSum减去targetSum。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int pathSumPrefixSum(TreeNode root, int targetSum) {
        // key: 前缀和，value：前缀和为key的路径的数量
        prefix.put(0L, 1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode root, long currentSum, int targetSum) {
        if (root == null) {
            return 0;
        }
        currentSum += root.val;
        // 维护前缀和
        prefix.put(currentSum, prefix.getOrDefault(currentSum, 0) + 1);

        int counter = prefix.getOrDefault(currentSum - targetSum, 0);
        counter += dfs(root.left, currentSum, targetSum);
        counter += dfs(root.right, currentSum, targetSum);

        // 维护前缀和
        prefix.put(currentSum, prefix.getOrDefault(currentSum, 0) - 1);

        return counter;
    }

    /**
     * 深度优先搜索
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        // 求以 root 为根的路径数量
        int counter = rootSum(root, targetSum);
        // 求以 root.left 为根的路径数量
        counter += pathSum(root.left, targetSum);
        // 求以 root.right 为根的路径数量
        counter += pathSum(root.right, targetSum);

        return counter;
    }

    private int rootSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }

        int counter = 0;
        if (root.val == targetSum) {
            counter++;
        }

        counter += rootSum(root.left, targetSum - root.val);
        counter += rootSum(root.right, targetSum - root.val);

        return counter;
    }

}
