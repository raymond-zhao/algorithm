package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import java.util.ArrayList;
import java.util.List;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 22:43
 * 题目：<a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&id=top-100-liked">114. 二叉树展开为链表</a>
 */
public class Hot114FlattenBinaryTreeToLinkedList {

    /**
     * 该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。
     * 算法流程：
     * 1. 对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点；
     * 2. 将当前节点的右子节点赋给前驱节点的右子节点；
     * 3. 将当前节点的左子节点赋给当前节点的右子节点；
     * 4. 将当前节点的左子节点设为空；
     * 5. 对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void flatten(TreeNode root) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            // 处理左子节点，如果左子节点为空则不需要展开。
            // 寻找左子节点的最右侧节点
            if (currentNode.left != null) {
                // 先保存当前节点的左子节点
                TreeNode left = currentNode.left;
                // 从当前节点的左子节点开始寻找其前驱节点（左子树中最右节点）
                TreeNode predecessor = left;
                while (predecessor.right != null) {
                    // 寻找左子树中的最优节点
                    predecessor = predecessor.right;
                }
                // 此时已经找到前驱节点，将前驱节点的右子节点指向当前节点的右子节点。
                predecessor.right = currentNode.right;
                // 置空左子节点的指针
                currentNode.left = null;
                // 移动到下一个需要处理的节点
                currentNode.right = left;
            }
            // 处理右子节点
            currentNode = currentNode.right;
        }
    }

    /**
     * 1. 使用先序遍历存储所有节点
     * 2. 遍历先序遍历结果集，修改每个节点的指针。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public void flattenII(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

}
