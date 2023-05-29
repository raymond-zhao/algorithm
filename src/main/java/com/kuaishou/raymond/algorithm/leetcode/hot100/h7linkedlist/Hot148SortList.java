package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import static com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist.Hot21MergeTwoSortedLists.mergeTwoLists;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

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
 * - 获取结点数字再重建
 */
public class Hot148SortList {

    public static void main(String[] args) {
        ListNode h = AlgoUtils.toLinkedList("[-3,11,14,15,19,1,4,5,5,8]");
        // AlgoUtils.printLinkedList(sortListIterative(h));
        ListNode listNode = sortListRecursive(h);
        AlgoUtils.printLinkedList(listNode);
    }

    /**
     * 自上而下使用递归进行归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static ListNode sortListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = getMiddleNode(head);
        ListNode middleNext = middleNode.next;
        // 将链表断开为前后两段
        middleNode.next = null;

        // 继续切割左半段链表
        ListNode left = sortListRecursive(head);
        // 继续切割右半段链表
        ListNode right = sortListRecursive(middleNext);

        // 合并两个有序链表
        return mergeTwoLists(left, right);
    }

    private static ListNode getMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 自下而上进行迭代合并
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public static ListNode sortListIterative(ListNode head) {
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
}
