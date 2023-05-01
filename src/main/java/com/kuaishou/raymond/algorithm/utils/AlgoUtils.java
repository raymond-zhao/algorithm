package com.kuaishou.raymond.algorithm.utils;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;
import com.kuaishou.raymond.algorithm.leetcode.TreeNode;
import com.kuaishou.raymond.algorithm.tree.TraverseTree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-28 19:09
 */
public class AlgoUtils {

    public static void main(String[] args) {
        String s = "[5,4,8,11,null,13,4,7,2,null,null,null,1]";
        String s1 = "[5,6,7,8,4,2,5,3,3,3]";
        TreeNode tree = toTree(s);
        System.out.println("toTree(s) = " + tree);
        TraverseTree.preorderRecursive(tree);
    }

    private AlgoUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static int[] toIntArray(String str) {
        String[] splited = toStringArray(str);
        List<Integer> data =
                Arrays.stream(splited).filter(Objects::nonNull).map(Integer::parseInt).collect(Collectors.toList());
        int[] res = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            res[i] = data.get(i);
        }
        return res;
    }

    /**
     * 构建包含 null 值的整型数组
     */
    public static Integer[] toIntegerArray(String str) {
        String[] stringArray = toStringArray(str);
        Integer[] data = new Integer[stringArray.length];
        for (int i = 0; i < data.length; i++) {
            if (!Objects.equals(stringArray[i], "null")) {
                data[i] = Integer.parseInt(stringArray[i]);
            } else {
                data[i] = null;
            }
        }
        return data;
    }

    /**
     * 数组中可能包含 null 值
     */
    public static String[] toStringArray(String str) {
        if (str == null || str.length() == 0) {
            return new String[0];
        }
        String replaced = str.replace("[", "").replace("]", "");
        return replaced.split(",");
    }

    public static ListNode toLinkedList(String str) {
        return buildLinkedList(toIntArray(str));
    }

    /**
     * 构建以完全二叉树形式存储的数
     * - 根节点从 0 开始
     * - 设某节点索引为 i
     * - 左子节点：2i+1
     * - 右子节点：2i+2
     */
    public static TreeNode toTree(String str) {
        String[] stringArray = toStringArray(str);
        return buildTree(stringArray);
    }

    public static TreeNode buildTree(String[] arr) {
        return buildTree(arr, 0);
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

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] t : matrix) {
            printRow(t);
        }
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

    public static void printLinkedList(ListNode head) {
        List<Integer> data = new ArrayList<>();
        while (head != null) {
            data.add(head.val);
            head = head.next;
        }
        System.out.println("data = " + data);
    }

    public static TreeNode buildTree(String[] arr, int i) {
        TreeNode root = null;
        // Base case for recursion
        if (i < arr.length) {
            if ("null".equals(arr[i])) {
                return null;
            } else {
                root = new TreeNode(Integer.parseInt(arr[i]));
            }

            // insert left child
            root.left = buildTree(arr, 2 * i + 1);

            // insert right child
            root.right = buildTree(arr, 2 * i + 2);
        }
        return root;
    }
}
