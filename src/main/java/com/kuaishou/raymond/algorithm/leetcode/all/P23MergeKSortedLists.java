package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static com.kuaishou.raymond.algorithm.leetcode.all.P21MergeTwoSortedLists.mergeTwoLists;

public class P23MergeKSortedLists {

    public static void main(String[] args) {
        int[] nums1 = new int[0];
        int[] nums2 = {1};
        ListNode[] lists = new ListNode[2];
        lists[0] = AlgoUtils.buildLinkedList(nums1);
        lists[1] = AlgoUtils.buildLinkedList(nums2);
        mergeKLists(lists);
    }

    /**
     * 暴力解，每次合并两个有序链表。
     * 时间复杂度：O(n*(l1+l2))，n 为链表数组的长度，l1, l2 为每次合并链表的平均长度。
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode list0 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            list0 = mergeTwoLists(list0, lists[i]);
        }
        return list0;
    }

    /**
     * 分治，每次操作 n 个链表。
     */
    public static ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public static ListNode divideAndConquer(ListNode[] lists, int left, int right) {
        // 递归终止条件
        if (left == right) {
            return lists[left];
        }
        int middle = (left + right) >>> 1;
        ListNode l1 = divideAndConquer(lists, left, middle);
        ListNode l2 = divideAndConquer(lists, middle + 1, right);
        return mergeTwo(l1, l2);
    }

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

    /**
     * 使用小根堆合并 k 个有序链表
     */
    public ListNode mergeKListsUsingMinHeap(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            // 1. 先把每个链表的头结点加入小根堆
            if (listNode != null)
                queue.offer(listNode);
        }
        // 2. 逐个迭代 并链接
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (!queue.isEmpty()) {
            ListNode minNode = queue.poll();
            prev.next = minNode;
            prev = minNode;
            if (minNode.next != null)
                queue.offer(minNode.next);
        }
        return dummy.next;
    }
}
