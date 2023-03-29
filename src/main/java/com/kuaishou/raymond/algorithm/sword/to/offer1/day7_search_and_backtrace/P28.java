package com.kuaishou.raymond.algorithm.sword.to.offer1.day7_search_and_backtrace;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

/**
 * 判断二叉树是不是对称的
 * 二叉树对称：如果一个二叉树与它的镜像相同，则称这个二叉树是对称的。
 */
public class P28 {

    public static void main(String[] args) {
        P28 p28 = new P28();
        System.out.println("p28.isSymmetric(TreeNode.defaultTree()) = " + p28.isSymmetric(TreeNode.defaultTree()));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return recur(left.right, right.left) && recur(left.left, right.right);
    }

}
