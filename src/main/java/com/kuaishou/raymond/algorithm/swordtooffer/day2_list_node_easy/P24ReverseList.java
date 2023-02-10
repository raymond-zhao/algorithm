package com.kuaishou.raymond.algorithm.swordtooffer.day2_list_node_easy;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

public class P24ReverseList {

    /**
     * head
     * |
     * 1->2->3->4->5->NULL
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            head = current.next;
            current.next = prev;
            prev = current;
            current = head;
        }
        return prev;
    }

}
