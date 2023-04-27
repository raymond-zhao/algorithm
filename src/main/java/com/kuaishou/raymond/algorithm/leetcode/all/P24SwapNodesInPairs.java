package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">...</a>
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * [1,2,3,4] -> [2,1,4,3]
 */
public class P24SwapNodesInPairs {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        ListNode head = AlgoUtils.buildLinkedList(nums);
//        ListNode listNode = swapPairs(head);
        ListNode listNode = swapPairsIteratively(head);
        System.out.println("listNode = " + listNode);
    }

    /**
     * 递归
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 保存同结点的下一个结点，将会成为反转后的头结点。
        ListNode next = head.next;
        // head 在反转结束 后会成为链表中的第二个结点，head.next 链接的实际上是反转后的第三个结点。
        head.next = swapPairs(next.next);
        // next 已经是新的头结点，让头结点指向第二个结点。
        next.next = head;
        // 返回新的头结点
        return next;
    }

    /**
     * 迭代
     */
    public static ListNode swapPairsIteratively(ListNode head) {
        if (head == null || head.next == null) {
            // 当结点为空或者只有一个结点时
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            ListNode slow = prev.next;
            ListNode fast = prev.next.next;
            prev.next = fast;
            slow.next = fast.next;
            fast.next = slow;
            prev = slow;
        }
        return dummyHead.next;
    }
}
