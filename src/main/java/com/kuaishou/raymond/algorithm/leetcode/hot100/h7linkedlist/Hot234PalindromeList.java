package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list/?envType=study-plan-v2&id=top-100-liked">234. 回文链表</a>
 * - 链表
 * - 链表中点
 * - 翻转链表
 */
public class Hot234PalindromeList {

    public static void main(String[] args) {
        ListNode head = AlgoUtils.buildLinkedList(AlgoUtils.toIntArray("[1,2,2,1]"));
        Hot234PalindromeList hot = new Hot234PalindromeList();
        System.out.println("hot.isPalindrome(head) = " + hot.isPalindrome(head));
        ListNode head2 = AlgoUtils.buildLinkedList(AlgoUtils.toIntArray("[1,2,2,1]"));
        System.out.println("hot.isPalindromeII(head) = " + hot.isPalindromeUsingList(head2));
    }

    /**
     * 1. 寻找链表中点
     * 2. 翻转后半段链表
     * 3. 从链表头部 head 开始与反转后的后半段链表作比较
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        // 1. 获取链表中间结点
        ListNode middleNode = getMiddleNode(head);
        // 翻转后半段链表
        ListNode latterNode = reverseList(middleNode);

        // 开始比较
        while (head != null && latterNode != null) {
            if (head.val != latterNode.val) {
                return false;
            }
            head = head.next;
            latterNode = latterNode.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode previous = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }

    public static ListNode getMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindromeUsingList(ListNode head) {
        if (head == null) {
            return false;
        }
        List<Integer> data = new ArrayList<>();
        while (head != null) {
            data.add(head.val);
            head = head.next;
        }

        return isPalindrome(data);
    }

    private boolean isPalindrome(List<Integer> data) {
        int left = 0;
        int right = data.size() - 1;
        while (left < right) {
            if (!Objects.equals(data.get(left), data.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
