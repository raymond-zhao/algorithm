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
     * 计算以 root 为根节点的子树的路径和，中途更新最大路径和（left+right+root）。
     */
    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxSum(root.left));
        int right = Math.max(0, maxSum(root.right));

        // 此方法的作用是返回每个节点能获取到的最大值，这里只是在返回之前先计算一下整体的最大值。
        // 整体的最大值 = root + left + right
        maxPathSum = Math.max(maxPathSum, left + right + root.val);

        // a + b + a 的父节点 || a + c + a 的父节点
        return root.val + Math.max(left, right);
    }

}
