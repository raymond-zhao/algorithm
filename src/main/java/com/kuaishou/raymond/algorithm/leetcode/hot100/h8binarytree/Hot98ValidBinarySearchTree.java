package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 21:26
 * 题目名称：98. 验证二叉搜索树
 * 题目链接：<a href="https://leetcode.cn/problems/validate-binary-search-tree/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot98ValidBinarySearchTree {

    /**
     * 核心思想：比较根节点与左右子节点的值的大小关系
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root == null) {
            // 遍历到叶子节点，返回 true。
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        // 验证左右子树
        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }

    /**
     * 中序遍历加提前返回
     */
    public boolean isValidBSTII(TreeNode root) {
        if (root == null) {
            return false;
        }

        long inorder = Long.MIN_VALUE;

        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                root = stack.pop();
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
        }
        return true;
    }

    /**
     * 核心思想：二叉搜素数的中序遍历结果递增
     * - 中序遍历得到结果集
     * - 遍历结果集，如果非单调递增，则不是二叉搜索树。
     */
    public boolean isValidBSTIII(TreeNode root) {
        if (root == null) {
            return false;
        }
        List<Integer> data = new ArrayList<>();
        inorderTraversal(data, root);

        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) <= data.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void inorderTraversal(List<Integer> data, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(data, root.left);
        data.add(root.val);
        inorderTraversal(data, root.right);
    }

}
