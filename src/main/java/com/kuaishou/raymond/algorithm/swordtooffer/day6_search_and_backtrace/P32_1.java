package com.kuaishou.raymond.algorithm.swordtooffer.day6_search_and_backtrace;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

import java.util.*;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class P32_1 {

    public static void main(String[] args) {
        P32_1 p321 = new P32_1();
        System.out.println("Arrays.toString(p321.levelOrder(TreeNode.defaultTree())) = " + Arrays.toString(p321.levelOrder(TreeNode.defaultTree())));
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<TreeNode> treeNodeList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentTreeNode = queue.poll();
            treeNodeList.add(currentTreeNode);
            if (currentTreeNode.left != null) {
                queue.offer(currentTreeNode.left);
            }
            if (currentTreeNode.right != null) {
                queue.offer(currentTreeNode.right);
            }
        }

        int[] res = new int[treeNodeList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = treeNodeList.get(i).val;
        }
        return res;
    }

}
