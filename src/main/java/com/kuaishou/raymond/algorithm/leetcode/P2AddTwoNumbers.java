package com.kuaishou.raymond.algorithm.leetcode;

import java.util.Objects;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-01-31 17:27
 */
public class P2AddTwoNumbers {

    public static void main(String[] args) {
        P2AddTwoNumbers p2 = new P2AddTwoNumbers();
        p2.addTwoNumbers(null, null);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1) && Objects.isNull(l2)) {
            return null;
        }
        ListNode pointer = new ListNode(-1);
        ListNode dummyHead = pointer;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int valueOfL1 = Objects.isNull(l1) ? 0 : l1.val;
            int valueOfL2 = Objects.isNull(l2) ? 0 : l2.val;
            int sum = valueOfL1 + valueOfL2 + carry;

            pointer.next = new ListNode(sum % 10);

            // 指针后移
            pointer = pointer.next;
            l1 = Objects.isNull(l1) ? null : l1.next;
            l2 = Objects.isNull(l2) ? null : l2.next;

            // 计算进位
            carry = sum / 10;
        }

        if (carry > 0) {
            pointer.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
