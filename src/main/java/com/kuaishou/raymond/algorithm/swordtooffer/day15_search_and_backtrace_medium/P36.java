package com.kuaishou.raymond.algorithm.swordtooffer.day15_search_and_backtrace_medium;

import com.kuaishou.raymond.algorithm.swordtooffer.Node;

/**
 * 二叉搜索树与双向循环排序链表
 * 二叉搜素树的中序遍历结果为有序序列，基于二叉搜索树建立双向循环链表，实际上是对二叉搜索树进行中序遍历，
 * 并且为每个遍历到的结点添加前驱与后继，并处理首尾结点的过程。
 */
public class P36 {

    private Node prev;

    private Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        dfs(root);
        head.left = prev;
        prev.right = head;

        return head;
    }

    private void dfs(Node node) {
        if (node == null) {
            return;
        }

        dfs(node.left);

        if (prev == null) {
            // 如果前驱结点为空，则当前结点为双向链表的头结点。
            head = node;
        } else {
            // 如果前驱结点不为空，则让前驱结点指向当前结点。
            prev.right = node;
        }
        node.left = prev;
        prev = node;

        dfs(node.right);
    }

}
