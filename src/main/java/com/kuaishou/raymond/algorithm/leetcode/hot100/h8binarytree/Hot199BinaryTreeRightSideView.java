package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 22:02
 * 题目：<a href="https://leetcode.cn/problems/binary-tree-right-side-view/?envType=study-plan-v2&id=top-100-liked">199. 二叉树的右视图</a>
 */
public class Hot199BinaryTreeRightSideView {

    /**
     * 使用层次遍历，但是只记录每层的最后一个元素。
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> data = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    data.add(node.val);
                }
            }
        }
        return data;
    }

}
