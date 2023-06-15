package com.kuaishou.raymond.algorithm.interview.pdd;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * Author: raymond
 * CreateTime: 2023/6/15 08:02
 * 题目：判断链表是否有环，如果有环则判断环的入口结点的编号。
 */
public class CircularList {

    public int positionOfCircleEntrance(ListNode head) {
        if (head == null) {
            return -1;
        }
        ListNode slow = head;
        ListNode fast = head;

        do {
            if (fast == null || fast.next == null) {
                return -1;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (fast != slow);
        // 跳出循环时，fast == slow。
        fast = head;
        int position = 1;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
            position++;
        }
        return position;
    }
}
