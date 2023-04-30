package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

import java.util.PriorityQueue;

import static com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist.Hot21MergeTwoSortedLists.mergeTwoLists;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 15:30
 * 题目名称：23. 合并 K 个升序链表
 * 题目链接：<a href="https://leetcode.cn/problems/merge-k-sorted-lists/?envType=study-plan-v2&id=top-100-liked">...</a>
 */
public class Hot23MergeKSortedLists {

    /**
     * 分治：比较高效的算法
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public static ListNode divideAndConquer(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int middle = (left + right) >>> 1;
        ListNode l1 = divideAndConquer(lists, left, middle);
        ListNode l2 = divideAndConquer(lists, middle + 1, right);
        return mergeTwoLists(l1, l2);
    }

    /**
     * 小根堆
     * 1. 将链表数组中的所有头结点放入小根堆中；
     * 2. 当小根堆非空时，每次取出小根堆堆顶元素，与结果集连接；
     * 3. 连接之后，将堆顶元素的下一个结点再存入小根堆
     */
    public static ListNode mergeKListsMinHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>();
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        while (!minHeap.isEmpty()) {
            ListNode minHead = minHeap.poll();
            node.next = minHead;
            node = node.next;
            if (minHead.next != null) {
                minHeap.offer(minHead.next);
            }
        }
        return dummyHead.next;
    }

    /**
     * 以第一个链表为基准，每次与链表数组中的下一个链表合并。
     * 即：两两合并
     */
    public static ListNode mergeKListsIII(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode l0 = lists[0];

        for (int i = 1; i < lists.length; i++) {
            l0 = mergeTwoLists(l0, lists[i]);
        }

        return l0;
    }


}
