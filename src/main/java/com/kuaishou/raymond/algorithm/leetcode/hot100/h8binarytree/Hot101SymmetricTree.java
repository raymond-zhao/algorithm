package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 18:26
 * 题目链接：<a href="https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&id=top-100-liked">101. 对称二叉树</a>
 * - 递归
 */
public class Hot101SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 递归判断两棵树是否对称
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

    /**
     * 递归判断两个树是否具有完全相同的结构
     */
    public static boolean isSameStructure(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSameStructure(left.left, right.left) && isSameStructure(left.right, right.right);
    }

    /**
     * 队列
     * @param left 左子树
     * @param right 右子树
     * @return 左右子树是否对称
     */
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
