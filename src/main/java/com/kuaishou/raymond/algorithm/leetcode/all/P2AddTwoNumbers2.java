package com.kuaishou.raymond.algorithm.leetcode.all;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/6/14 07:38
 * 题目：<a href="https://leetcode.cn/problems/add-two-numbers-ii/submissions/">445. 两数相加 II</a>
 */
public class P2AddTwoNumbers2 {

    /**
     * 头插法创建链表
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        // 从栈顶到栈底分别为「个，十，百，千...」位上的数字
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;

        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = num1 + num2 + carry;
            carry = sum / 10;

            // 头插法创建链表的核心代码
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }

        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 ==  null) {
            return l1;
        }
        ListNode h1 = reverseList(l1);
        ListNode h2 = reverseList(l2);

        return addTwoNumbersHelper(h1, h2);
    }

    private ListNode addTwoNumbersHelper(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;

            node.next = new ListNode(sum % 10);
            carry = sum / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            node = node.next;
        }
        if (carry != 0) {
            node.next = new ListNode(carry);
        }
        return reverseList(dummyHead.next);
    }

    private ListNode reverseList(ListNode head) {
        ListNode previous = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }
}
