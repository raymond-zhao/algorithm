package com.kuaishou.raymond.algorithm.swordtooffer.day18_search_and_backtrace_medium;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

/**
 * 二叉树的深度
 */
public class P55_1 {

    public static void main(String[] args) {
        P55_1 p551 = new P55_1();
        System.out.println("p551.maxDepth(TreeNode.defaultTree()) = " + p551.maxDepth(TreeNode.defaultTree()));
    }

    /**
     *            3
     *           / \
     *          9  20
     *            /  \
     *           15  7
     *
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

}
