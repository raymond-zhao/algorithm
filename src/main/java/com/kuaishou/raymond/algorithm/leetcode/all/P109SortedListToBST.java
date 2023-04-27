package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-23 20:42
 */
public class P109SortedListToBST {

    /**
     * 有序链表转二叉搜索树
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode left, ListNode right) {
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
    private ListNode getMiddleNode(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left.next;
        while (fast != null && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
