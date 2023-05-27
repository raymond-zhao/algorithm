package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * 题目链接：<a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&id=top-100-liked">19. 删除链表的倒数第 N 个结点</a>
 * - Medium
 * - 链表
 * - 快慢指针
 */
public class Hot19RemoveNthNodeFromEnd {

    public static void main(String[] args) {
        ListNode head = AlgoUtils.toLinkedList("[1,2,3,4,5]");
        Hot19RemoveNthNodeFromEnd hot = new Hot19RemoveNthNodeFromEnd();
        hot.removeNthFromEnd(head, 2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            // 先走 n 步
            if (fast != null) {
                fast = fast.next;
            } else {
                return dummyHead.next;
            }
        }

        // 此时 fast 指向 head 之后的第 N 个结点。
        // 声明变量 previous 保留倒数第 N+1 个结点，以便与删除后的链表相连。
        ListNode previous = dummyHead;
        while (fast != null) {
            fast = fast.next;
            previous = previous.next;
        }

        previous.next = previous.next.next;
        return dummyHead.next;
    }

}

