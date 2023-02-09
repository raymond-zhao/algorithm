package com.kuaishou.raymond.algorithm.swordtooffer;

public class TreeNode {
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     *            3
     *           / \
     *          9  20
     *            /  \
     *           15  7
     *
     */
    public static TreeNode defaultTree() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        return root;
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
