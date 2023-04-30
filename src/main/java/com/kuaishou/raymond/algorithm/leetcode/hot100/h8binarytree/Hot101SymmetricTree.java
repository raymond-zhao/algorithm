package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 18:26
 * 题目名称：101. 对称二叉树
 * 题目链接：<a href="https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot101SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 递归
     */
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            // 如果左节点与右节点均为 null，认为是对称。
            return true;
        }
        // 如果 left 与 right 不是同时为 null，而是只有其中一个为 null。
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }

    public boolean isSymmetricII(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(left);
        queue.offer(right);

        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
    }
}
