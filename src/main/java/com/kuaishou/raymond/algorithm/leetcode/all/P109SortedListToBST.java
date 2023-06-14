package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.leetcode.TreeNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-23 20:42
 * <a href="https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/">109. 有序链表转换二叉搜索树</a>
 */
public class P109SortedListToBST {

    public static void main(String[] args) {
        ListNode list = AlgoUtils.toLinkedList("[-10,-3,0,5,9]");
        sortedListToBST(list);
    }

    /**
     * 有序链表转二叉搜索树
     */
    public static TreeNode sortedListToBST(ListNode head) {
        return buildBST(head, null);
    }

    private static TreeNode buildBST(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode middleNode = getMiddleNode(left, right);
        TreeNode root = new TreeNode(middleNode.val);
        root.left = buildBST(left, middleNode);
        root.right = buildBST(middleNode.next, right);
        return root;
    }

    /**
     * 寻找链表的中点
     * @param left 链表头结点
     * @param right 链表尾结点
     * @return 链表中间结点
     */
    private static ListNode getMiddleNode(ListNode left, ListNode right) {
        ListNode slow = left;
        // fast=left 或者 fast=left.next 都可以 AC。
        ListNode fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
