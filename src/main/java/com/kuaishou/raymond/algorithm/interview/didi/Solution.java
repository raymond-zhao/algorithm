package com.kuaishou.raymond.algorithm.interview.didi;

/**
 * Author: raymond
 * CreateTime: 2023/5/18 19:36
 * 题目：k个一组，翻转链表； 比如1-2-3-4-5，k=2，翻转后未2-1-4-3-5
 */
public class Solution {

   private static class ListNode {

       private final int val;

       private ListNode next;

       public ListNode(int val) {
           this.val = val;
       }
   }

    public static void main(String[] args) {
       ListNode head = new ListNode(1);
       ListNode node2 = new ListNode(2);
       ListNode node3 = new ListNode(3);
       ListNode node4 = new ListNode(4);
       ListNode node5 = new ListNode(5);
       head.next = node2;
       node2.next = node3;
       node3.next = node4;
       node4.next = node5;
       node5.next = null;

       reverseListKGroup(head, 2);
    }

   public static ListNode reverseListKGroup(ListNode head, int k) {
       if (head == null) {
           return null;
       }
       ListNode dummyHead = new ListNode(-1);
       dummyHead.next = head;

       // previous: K 个链表的头结点
       ListNode previous = dummyHead;

       // ListNode end = head; // 第一个错误点，不应该指向 head，而应该指向 dummyHead。
       ListNode end = dummyHead;

       while (true) {
           // 快指针先走 k 个点
           for (int i = 0; i < k && end != null; i++) {
               end = end.next;
           }
           if (end == null) {
               // 如果当前要反转的链表已不足K个结点，则不做翻转，跳出循环。
               break;
           }
           // end 表示本组要翻转的尾结点，保存 end 的下一个结点
           ListNode headOfNextGroup = end.next;
           // 置空当前组的最后一个结点
           end.next = null;
           
           // 本组要反转的头结点
           ListNode tailNode = previous.next;
           // 从 tailNode~end 之间的结点为需要反转的结点
           previous.next = reverseList(tailNode);
           
           // 准备下一轮的结点
           tailNode.next = headOfNextGroup;
           previous = tailNode;
           end = tailNode;
       }

       return dummyHead.next;
   }

    private static ListNode reverseList(ListNode head) {
       if (head == null) {
           return null;
       }

       ListNode previous = null;
       // 1->2->3->4->5->null
       while (head != null) {
           ListNode next = head.next;
           head.next = previous;
           previous = head;
           head = next;
       }
       return previous;
    }
}
