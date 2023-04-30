package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 19:26
 * 题目名称：543. 二叉树的直径
 * 题目链接：<a href="https://leetcode.cn/problems/diameter-of-binary-tree/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot543DiameterOfBinaryTree {

    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 1;
        dfs(root);
        return diameter - 1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        diameter = Math.max(diameter, leftDepth + rightDepth + 1);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
