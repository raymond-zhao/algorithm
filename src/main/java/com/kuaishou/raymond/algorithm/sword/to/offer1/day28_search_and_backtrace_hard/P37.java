package com.kuaishou.raymond.algorithm.sword.to.offer1.day28_search_and_backtrace_hard;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

/**
 * 序列化二叉树
 */
public class P37 {

    public static void main(String[] args) {
        String data = serialize(TreeNode.defaultTree());
        System.out.println("serialize(TreeNode.defaultTree()) = " + data);
        TreeNode root = deserialize(data);
    }

    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        Queue<TreeNode> queue = new LinkedList<>(); // 不能使用 ArrayDeque
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                builder.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                builder.append("null,");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(']');
        return builder.toString();
    }

    public static TreeNode deserialize(String data) {
        if (Objects.equals(data, "[]")) {
            return null;
        }
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);
        int index = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!"null".equals(nodes[index])) {
                node.left = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.left);
            }
            index++;

            if (!"null".equals(nodes[index])) {
                node.right = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.right);
            }
            index++;
        }
        return root;
    }

}
