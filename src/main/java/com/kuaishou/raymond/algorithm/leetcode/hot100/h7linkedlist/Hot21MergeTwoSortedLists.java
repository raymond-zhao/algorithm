package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 21. 合并两个有序链表
 */
public class Hot21MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list2 == null) {
            return list1;
        }
        if (list1 == null) {
            return list2;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        // 跳出循环时，list1 == null || list2 == null，说明链表之一可能还不为空，可以直接拼接起来。
        node.next = list1 != null ? list1 : list2;
        return dummyHead.next;
    }

    /**
     * 递归
     */
    private static ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwo(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwo(l1, l2.next);
        return l2;
    }
}
