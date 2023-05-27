package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/?envType=study-plan-v2&id=top-100-liked">24. 两两交换链表中的节点</a>
 * - Medium
 * - 链表
 * - 相似题目
 *  - {@link Hot206ReverseList}
 *  - {@link Hot25SwapNodesInKGroup}
 */
public class Hot24SwapNodesInPairs {

    /**
     * 两两一组反转链表：迭代法
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            // 当结点为空或者只有一个结点时
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            ListNode slow = prev.next;
            ListNode fast = prev.next.next;
            prev.next = fast;
            slow.next = fast.next;
            fast.next = slow;
            prev = slow;
        }
        return dummyHead.next;
    }

    /**
     * 两两一组反转链表：递归法
     */
    public ListNode swapPairsII(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 保存同结点的下一个结点，将会成为反转后的头结点。
        ListNode newHead = head.next;
        // head 在反转结束 后会成为链表中的第二个结点，head.newHead 链接的实际上是反转后的第三个结点。
        head.next = swapPairsII(newHead.next);
        // newHead 已经是新的头结点，让头结点指向第二个结点。
        newHead.next = head;
        // 返回新的头结点
        return newHead;
    }

}
