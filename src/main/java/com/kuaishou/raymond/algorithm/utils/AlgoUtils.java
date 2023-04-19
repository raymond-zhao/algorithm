package com.kuaishou.raymond.algorithm.utils;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

import java.util.Arrays;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-28 19:09
 */
public class AlgoUtils {

    private AlgoUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] t : matrix) {
            printRow(t);
        }
    }

    public static ListNode buildLinkedList(int[] nums) {
        ListNode head = new ListNode(-1);
        ListNode node = head;
        for (int num : nums) {
            node.next = new ListNode(num);
            node = node.next;
        }
        return head.next;
    }

    public static <T> void printRow(T[] row) {
        System.out.println("Arrays.toString(row) = " + Arrays.toString(row));
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            printRow(row);
        }
    }

    public static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            printRow(row);
        }
    }

    public static void printRow(int[] row) {
        System.out.println("Arrays.toString(row) = " + Arrays.toString(row));
    }

    public static void printRow(boolean[] row) {
        System.out.println("Arrays.toString(row) = " + Arrays.toString(row));
    }
}
