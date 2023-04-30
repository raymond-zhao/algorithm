package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 16:52
 * 题目名称：94. 二叉树的中序遍历
 * 题目链接：<a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot94BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            // 走到左下角
            while (node != null) {
                // 将当前节点加入栈
                stack.push(node);
                // 前往左子节点
                node = node.left;
            }
            // 退出上面循环时，node = null。
            // 如果栈非空，出栈，先访问出栈节点，然后访问该节点的右子节点。
            if (!stack.isEmpty()) {
                node = stack.pop();
                data.add(node.val);
                node = node.right;
            }
        }
        return data;
    }

    /**
     * 递归
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        dfs(data, root);
        return data;
    }

    private void dfs(List<Integer> data, TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(data, root.left);
        data.add(root.val);
        dfs(data, root.right);
    }
}
