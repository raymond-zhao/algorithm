package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/28 10:34
 * 题目：
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode h1 = AlgoUtils.toLinkedList("[19,1,4,5,5,8]");
        // 当链表结点为奇数时，两个方法返回的结果相同。
        // 当链表结点为偶数时，第二个方法比第一个方法多返回一个结点。
        AlgoUtils.printLinkedList(getMiddleNodeI(h1));
        AlgoUtils.printLinkedList(getMiddleNodeII(h1));
    }

    public static ListNode getMiddleNodeI(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode getMiddleNodeII(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow =  head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
