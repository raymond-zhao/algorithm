package com.kuaishou.raymond.algorithm.sword.to.offer1.day6_search_and_backtrace;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

import java.util.*;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class P32_2 {

    public static void main(String[] args) {
        P32_2 p322 = new P32_2();
        p322.levelOrder(TreeNode.defaultTree()).forEach(System.out::println);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<List<Integer>> data = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null) {
                    currentLevel.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }
            }
            data.add(currentLevel);
        }
        return data;
    }
}

