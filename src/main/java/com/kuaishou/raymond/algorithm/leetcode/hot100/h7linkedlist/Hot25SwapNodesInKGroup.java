package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/?envType=study-plan-v2&id=top-100-liked">25. K 个一组翻转链表</a>
 * - Hard
 * - 链表
 * - 反转链表
 * - 双指针
 */
public class Hot25SwapNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // previous 指向本次要反转的 K 个结点的头结点的上一个结点
        ListNode previous = dummyHead;
        // end 指向本次要反转的 K 个结点的最后一个结点
        ListNode end = dummyHead;

        while (true) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // [previous.next, end] 之间都是本轮要翻转的链表
            if (end == null) {
                break;
            }

            // 1. 保存下一个 K 组中的头结点
            ListNode nextKGroupHead = end.next;
            // 2. 与下一个 K 组及之后的结点断链
            end.next = null;

            // 3. previous.next 为本轮要反转的 K 个结点的头结点，在反转后将会成为本轮 K 个链表的尾结点，
            // 需要保存下来，与下一个 K 组的头结点相连接，即 1. 保存的结点。
            ListNode tailNode = previous.next;
            if (tailNode == null) {
                break;
            }
            // 4. 从本轮 K 个结点中的头结点开始反转
            previous.next = reverse(tailNode);

            // 5. 连接本轮反转后的 K 个结点与下个 K 组的头结点
            tailNode.next = nextKGroupHead;

            // 6. 使 previous 与 end 重新指向本轮反转后的尾结点，作为下一个 K 组中的头结点的上一个结点。
            end = tailNode;
            previous = tailNode;
        }

        return dummyHead.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode previous = null;
        while (head != null) {
            ListNode headNext = head.next;
            head.next = previous;
            previous = head;
            head = headNext;
        }

        return previous;
    }
}
