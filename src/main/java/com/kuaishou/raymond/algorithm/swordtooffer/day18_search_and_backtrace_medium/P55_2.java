package com.kuaishou.raymond.algorithm.swordtooffer.day18_search_and_backtrace_medium;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

/**
 * 平衡二叉树
 * 如果任意结点的两棵子树高度相差不大于 1，则为平衡二叉树。
 */
public class P55_2 {

    public static void main(String[] args) {
        P55_2 p552 = new P55_2();
        System.out.println("p552.isBalanced(TreeNode.defaultTree()) = " + p552.isBalanced(TreeNode.defaultTree()));
        System.out.println("p552.isBalanced2(TreeNode.defaultTree()) = " + p552.isBalanced2(TreeNode.defaultTree()));
    }

    public boolean isBalanced2(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = recur(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = recur(root.right);
        if (rightDepth == -1) {
            return -1;
        }

        return Math.abs(leftDepth - rightDepth) < 2 ? Math.max(leftDepth, rightDepth) + 1 : -1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        if (Math.abs(left - right) <= 1) {
            return true;
        }
        return isBalanced(root) && isBalanced(root.left) && isBalanced(root.right);
    }

    private int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(root.left), treeDepth(root.right));
    }

}
