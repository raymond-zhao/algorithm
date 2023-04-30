package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 18:16
 * 题目名称：226. 翻转二叉树
 * 题目链接：<a href="https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot226InvertBinaryTree {

    /**
     * 递归翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }

    /**
     * 迭代翻转二叉树
     */
    public TreeNode invertTreeII(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 入栈当前节点的左右子节点
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            // 交换左右子树
            TreeNode tempNode = node.left;
            node.left = node.right;
            node.right = tempNode;
        }
        return root;
    }
}
