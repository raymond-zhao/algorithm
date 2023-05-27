package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * 题目链接：<a href="https://leetcode.cn/problems/add-two-numbers/?envType=study-plan-v2&id=top-100-liked">2. 两数相加</a>
 * - Medium
 * - 链表
 * - 链表逆序存储刚好满足了数字相加时从个位相加这个规律
 */
public class Hot2AddTwoNumber {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;

            node.next = new ListNode(sum % 10);
            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            node = node.next;
        }

        if (carry != 0) {
            node.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

}
