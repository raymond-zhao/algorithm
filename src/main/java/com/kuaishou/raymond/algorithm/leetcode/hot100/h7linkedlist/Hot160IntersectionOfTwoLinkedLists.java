package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 160. 相交链表
 */
public class Hot160IntersectionOfTwoLinkedLists {

    /**
     * 我吹过你吹过的风，这算不算相拥？
     * 我走过你走过的路，这算不算相逢？
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha = headA;
        ListNode hb = headB;
        while (ha != hb) {
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
    }

}
