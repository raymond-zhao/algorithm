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
}
