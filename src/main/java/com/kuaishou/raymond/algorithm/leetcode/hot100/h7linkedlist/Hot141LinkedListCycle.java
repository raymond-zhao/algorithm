package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/?envType=study-plan-v2&id=top-100-liked">141. 环形链表</a>
 * - 链表
 * - 双指针
 * - 快慢指针
 */
public class Hot141LinkedListCycle {

    /**
     * 快慢指针
     * 如果有环，最后一定会相遇。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleII(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                // 如果可以走到头，说明无环。
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
    }
}
