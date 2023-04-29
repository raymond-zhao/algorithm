package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 21. 合并两个有序链表
 */
public class Hot21MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        if (list1 != null) {
            node.next = list1;
        }

        if (list2 != null) {
            node.next = list2;
        }

        return dummyHead.next;
    }
}
