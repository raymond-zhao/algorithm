package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * 题目：<a href="https://leetcode.cn/problems/linked-list-cycle-ii/?envType=study-plan-v2&id=top-100-liked">142. 环形链表II</a>
 * 题目解析：<a href="https://leetcode.cn/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/">142. 环形链表II</a>
 * - 链表
 * - 快慢指针
 * - 双指针
 */
public class Hot142LinkedListCycleII {

    /**
     * 快慢指针
     * 如果有环，最后一定会相遇，当相遇时，让快指针从头开始，
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        do {
            if (fast == null || fast.next == null) {
                // 如果可以走到链表尾部，说明无环。
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
