package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 20:54
 * 题目名称：102. 二叉树的层序遍历
 * 题目链接：<a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">...</a>
 */
public class Hot102BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> data = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            data.add(currentLevel);
        }
        return data;
    }

}
