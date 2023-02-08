package com.kuaishou.raymond.algorithm.swordtooffer.day11_bipointer;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/?favorite=xb9nqhhg">18.删除链表的结点</a>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点，返回删除后的链表的头节点。
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 */
public class P18 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        P18 p18 = new P18();
        ListNode dummy = p18.deleteNode(head, 5);
        while (dummy != null) {
            System.out.println("dummy.val = " + dummy.val);
            dummy = dummy.next;
        }
    }

    public ListNode deleteNode2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode prev = head;
        ListNode current = head.next;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
                break;
            }
            current = current.next;
            prev = prev.next;
        }
        return head;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }

        ListNode dummy = head;
        ListNode prev = head;
        for (int i = 0; i < 1; i++) {
            head = head.next;
        }

        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
                break;
            }
            head = head.next;
            prev = prev.next;
        }

        return dummy;
    }

}
