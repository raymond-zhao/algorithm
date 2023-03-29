package com.kuaishou.raymond.algorithm.sword.to.offer1.day12_bipointer;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * 相交链表/两个链表的第一个公共结点
 */
public class P52 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headB == null || headA == null) {
            return null;
        }
        ListNode l1 = headA;
        ListNode l2 = headB;

        while (l1 != l2) {
            l1 = l1 != null ? l1.next : headB;
            l2 = l2 != null ? l2.next : headA;
        }
        return l1;
    }

}
