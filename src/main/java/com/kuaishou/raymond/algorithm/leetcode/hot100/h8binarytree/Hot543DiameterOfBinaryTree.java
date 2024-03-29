package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 19:26
 * 题目链接：<a href="https://leetcode.cn/problems/diameter-of-binary-tree/?envType=study-plan-v2&id=top-100-liked">543. 二叉树的直径</a>
 */
public class Hot543DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;

        Hot543DiameterOfBinaryTree hot543 = new Hot543DiameterOfBinaryTree();
        System.out.println("hot543.diameterOfBinaryTree(root) = " + hot543.diameterOfBinaryTree(root));
    }

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
