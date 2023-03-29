package com.kuaishou.raymond.algorithm.sword.to.offer1.day6_search_and_backtrace;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 答案
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class P32_3 {

    public static void main(String[] args) {
        P32_3 p323 = new P32_3();
        // p323.levelOrder(TreeNode.defaultTree()).forEach(System.out::println);
        p323.levelOrder2(TreeNode.defaultTree()).forEach(System.out::println);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> data = new LinkedList<>();
        while (!queue.isEmpty()) {
            LinkedList<Integer> levelData = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();

                if (node == null) {
                    continue;
                }

                if (data.size() % 2 == 0) {
                    levelData.addLast(node.val);
                } else {
                    levelData.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            data.add(levelData);
        }
        return data;
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
            // 从左向右为 true
            for (int i = 0; i < size; i++) {
                // for 循环控制当前层的结点数量
                // 标志位控制打印的方向，
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
            if (data.size() % 2 == 1) {
                Collections.reverse(currentLevel);
            }
            data.add(currentLevel);
        }
        return data;
    }
}
