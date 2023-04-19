package com.kuaishou.raymond.algorithm.leetcode;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-19 13:06
 */
public class P19RemoveNthFromEnd {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] nums1 = {1};
        ListNode head = AlgoUtils.buildLinkedList(nums);
        removeNthFromEnd(head, 2);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        while (n >= 0 && fast != null) {
            // 题目中限定了 n <= 结点数量，所以这里可以不判空。
            // 因为 fast 是从 dummyHead 开始的，而且 dummyHead.next=head，所以这里的条件是 n>=0，循环次数需要比 n 多 1 次。
            fast = fast.next;
            n--;
        }

        // 此时，slow 从 dummyHead 重新开始
        ListNode slow = dummyHead;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 因为 fast 比 slow 多走了 n+1 个结点。所以当 fast 走到链表结尾的 null 处时，
        // slow 指向的是倒数第 n+1 个结点，直接让 slow 指向倒数第 n-1 个结点，则可以达到删除倒数第 n 个结点的效果。
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
