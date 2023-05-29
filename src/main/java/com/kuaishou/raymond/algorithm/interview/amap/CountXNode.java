package com.kuaishou.raymond.algorithm.interview.amap;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.Objects;

/**
 * Author: raymond
 * CreateTime: 2023/5/29 23:18
 * 题目：
 * 对于二叉树上的一个结点，若他的左子树和右子树完全相同（包括值和结构），则我们将他称为 “X结点”。
 * 现任意给定一颗二叉树，求这颗二叉树上所有 “X结点” 的个数。
 */
public class CountXNode {

    public static void main(String[] args) {
        System.out.println("countXNode(TreeNode.defaultSearchTree()) = " + countXNodes(TreeNode.defaultSearchTree()));
        System.out.println("countXNode(new TreeNode(1)) = " + countXNodes(new TreeNode(1)));
    }

    public static int countXNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int counter = 0;
        if (isSame(root, root)) {
            counter += 1;
        }

        counter += countXNodes(root.left);
        counter += countXNodes(root.right);

        return counter;
    }

    /**
     * 面试时写的，错了。
     */
    public static int countXNode(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        // 遍历树中的每个结点，获取 X 结点的个数。
        int xNodeCount = 0;
        dfs(root, xNodeCount);
        return xNodeCount;
    }

    private static void dfs(TreeNode root, int xNodeCount) {
        if (root == null) {
            return;
        }
        dfs(root.left, xNodeCount);
        if (isSame(root.left, root.right)) {
            xNodeCount++;
        }
        dfs(root.right, xNodeCount);
    }

    /**
     * @param left: 左子树
     * @param right: 右子树
     * @return 判断两棵树的结构是否完全相同
     */
    private static boolean isSame(TreeNode left, TreeNode right) {
        if (Objects.isNull(left) && Objects.isNull(right)) {
            return true;
        }
        if (Objects.isNull(left) || Objects.isNull(right)) {
            return false;
        }
        return left.val == right.val && isSame(left.left, right.left) && isSame(left.right, right.right);
    }
}
