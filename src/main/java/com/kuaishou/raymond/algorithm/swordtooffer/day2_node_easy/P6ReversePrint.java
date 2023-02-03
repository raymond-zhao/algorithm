package com.kuaishou.raymond.algorithm.swordtooffer.day2_node_easy;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

import java.util.*;

public class P6ReversePrint {

    private List<Integer> list = new ArrayList<>();

    public int[] reversePrint2(ListNode head) {
        recursion(head);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void recursion(ListNode head) {
        if (head == null) {
            return;
        }
        recursion(head.next);
        list.add(head.val);
    }

    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int[] res = new int[stack.size()];
        int idx = 0;
        while (!stack.isEmpty()) {
            res[idx++] = stack.pop();
        }
        return res;
    }

}
