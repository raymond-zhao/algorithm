package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-15 09:47
 */
public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int val;

    public TreeNode(int value) {
        this.val = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }

    /**
     *         3
     *        / \
     *       1   4
     *        \
     *         2
     */
    public static TreeNode defaultSearchTree() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        return root;
    }
}
