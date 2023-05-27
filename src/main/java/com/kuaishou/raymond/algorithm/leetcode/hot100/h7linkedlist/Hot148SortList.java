package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist.Hot21MergeTwoSortedLists.mergeTwoLists;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 12:58
 * 题目链接：<a href="https://leetcode.cn/problems/sort-list/?envType=study-plan-v2&id=top-100-liked">148. 排序链表</a>
 * - Medium
 * - 链表
 * - 分治
 * - 归并排序
 * - 寻找链表中点
 * - 合并两个有序链表
 */
public class Hot148SortList {

    public static void main(String[] args) {
        ListNode headB = AlgoUtils.toLinkedList("[-3,1,4,5,5,8,11,14,15,19]");
        ListNode head = AlgoUtils.toLinkedList("[4,2,1,3]");
        ListNode newHead = sortList(head);
        AlgoUtils.printLinkedList(newHead);
    }

    /**
     * 自下而上进行迭代合并
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 统计链表结点个数
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 声明伪头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 自底向上合并
        for (int subLength = 1; subLength < length; subLength *= 2) {
            ListNode previous = dummyHead;
            ListNode current = dummyHead.next;
            while (current != null) {
                ListNode headA = current;
                for (int i = 1; i < subLength && current.next != null; i++) {
                    current = current.next;
                }

                ListNode headB = current.next;
                current.next = null;
                current = headB;
                for (int i = 1; i < subLength && current != null && current.next != null; i++) {
                    current = current.next;
                }

                ListNode next = null;
                if (current != null) {
                    next = current.next;
                    current.next = null;
                }

                previous.next = mergeTwoLists(headA, headB);
                while (previous.next != null) {
                    previous = previous.next;
                }
                current = next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 自上而下使用递归进行归并排序
     * - 分而治之
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static ListNode sortListMergeSort(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 归并排序
     * 1. 递归终止条件
     */
    private static ListNode sortList(ListNode head, ListNode tail) {
        // 1. 递归终止条件: 链表之间没有结点，或者只有一个结点。
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            // 为什么要置空？因为要将链表断开为前后两段
            head.next = null;
            return head;
        }
        // 2. 寻找链表中点
        ListNode middleNode = getMiddleNode(head, tail);

        // 3. 递归前后两段链表
        ListNode l1 = sortList(head, middleNode);
        ListNode l2 = sortList(middleNode, tail);

        // 4. 合并两个有序链表
        return mergeTwoLists(l1, l2);
    }

    public static ListNode getMiddleNode(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * 使用列表存储所有值，然后再根据值新建链表。
     */
    public ListNode sortListBrutalForce(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> data = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            data.add(node.val);
            node = node.next;
        }

        Collections.sort(data);

        ListNode dummyHead = new ListNode(-1);
        node = dummyHead;
        for (int num : data) {
            node.next = new ListNode(num);
            node = node.next;
        }
        return dummyHead.next;
    }

}
