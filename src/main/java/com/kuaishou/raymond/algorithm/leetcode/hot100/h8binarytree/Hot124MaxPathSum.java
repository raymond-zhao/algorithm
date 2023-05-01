package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 11:20
 * 题目：<a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&id=top-100-liked">124. 二叉树中的最大路径和</a>
 * 相似题目：{@link Hot437PathSumIII}
 */
public class Hot124MaxPathSum {

    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return maxPathSum;
    }

    /**
     *          a
     *         / \
     *        b   c
     * 只有三种情况:
     * a + b + a 的父节点
     * a + c + a 的父节点
     * a + b + c
     */
    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxSum(root.left));
        int right = Math.max(0, maxSum(root.right));

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值之和
        maxPathSum = Math.max(maxPathSum, left + right + root.val);

        // a + b + a 的父节点 || a + c + a 的父节点
        return root.val + Math.max(left, right);
    }

}
