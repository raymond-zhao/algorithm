package com.kuaishou.raymond.algorithm.utils;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static int[] toArray(String str) {
        String replaced = str.replace("[", "").replace("]", "");
        String[] splited = replaced.split(",");
        List<Integer> data =
                Arrays.stream(splited).filter(Objects::nonNull).map(Integer::parseInt).collect(Collectors.toList());
        int[] res = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            res[i] = data.get(i);
        }
        return res;
    }

    public static ListNode toLinkedList(String str) {
        return buildLinkedList(toArray(str));
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

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
