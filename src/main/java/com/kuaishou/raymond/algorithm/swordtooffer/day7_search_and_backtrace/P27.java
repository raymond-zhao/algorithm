package com.kuaishou.raymond.algorithm.swordtooffer.day7_search_and_backtrace;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入一个二叉树，输出它的镜像。
 */
public class P27 {

    public static void main(String[] args) {
        P27 p27 = new P27();
        System.out.println("p27.mirrorTree(TreeNode.defaultTree()) = " + p27.mirrorTree(TreeNode.defaultTree()));
        System.out.println("p27.mirrorTree2(TreeNode.defaultTree()) = " + p27.mirrorTree2(TreeNode.defaultTree()));
    }

    /**
     * 递归
     */
    @Nullable
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        return root;
    }

    /**
     * 迭代
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
        }
        return root;
    }
}
