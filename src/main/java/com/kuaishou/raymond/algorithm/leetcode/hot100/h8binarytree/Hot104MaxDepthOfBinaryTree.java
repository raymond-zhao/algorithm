package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 17:19
 * 题目名称：104. 二叉树的最大深度
 * 题目链接：<a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">...</a>
 */
public class Hot104MaxDepthOfBinaryTree {

    /**
     * 递归
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i >= 0; i--) {
                // 将当前层的结点全部出队，将下层结点全部入队。
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

}
