package com.kuaishou.raymond.algorithm.tree;

import static io.vavr.API.print;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-15 09:46
 *          18
 *         /  \
 *        12   10
 *       / \   /\
 *      7  4  2  21
 *        /
 *       5
 * 先序遍历：根左右
 * 中序遍历：左根右
 * 后序遍历：左右根
 */
@Slf4j
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode root = constructTree();
        print("先序遍历(递归): ");
        preorderRecursive(root);
        System.out.println();
        print("先序遍历(迭代): ");
        preorderIterative(root);
        System.out.println();

        print("中序遍历(递归): ");
        middleOrderRecursive(root);
        System.out.println();
        print("中序遍历(迭代): ");
        middleOrderIterative(root);
        System.out.println();

        print("后序遍历(递归): ");
        postOrderRecursive(root);
        System.out.println();
        print("后序遍历(迭代): ");
        postOrderIterative(root);
        System.out.println();

        print("层次遍历(迭代): ");
        levelTravelIterative(root);
        System.out.println();

        print("Z 遍历(迭代): ");
        zTravelIterative(root);
        System.out.println();
    }

    private static void zTravelIterative(TreeNode root) {

    }

    /**
     * 从上到下层次遍历
     */
    private static void levelTravelIterative(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            print(node.getKey() + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 左右根
     */
    private static void postOrderIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        TreeNode lastVisited = null;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.peek();
            if (node.right == null || node.right == lastVisited) {
                // 如果右子树为空或者右子结点刚被访问过，则可输出该结点。
                node = stack.pop();
                print(node.getKey() + " ");
                lastVisited = node;
                node = null;
            } else {
                node = node.right;
            }
        }
    }

    /**
     * 左根右
     */
    private static void middleOrderIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                print(node.getKey() + " ");
                node = node.right;
            }
        }
    }

    private static void preorderIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            // 如果某个结点不为空，说明它还未被访问；
            // 如果栈不为空，说明它还有左孩子未被访问。
            while (node != null) {
                print(node.getKey() + " ");
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
       }
    }

    private static void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        print(root.getKey() + " ");
    }

    private static void middleOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        middleOrderRecursive(root.left);
        print(root.getKey() + " ");
        middleOrderRecursive(root.right);
    }

    private static void preorderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        print(root.getKey() + " ");
        preorderRecursive(root.left);
        preorderRecursive(root.right);
    }

    private static TreeNode constructTree() {
        TreeNode root = new TreeNode(18);

        TreeNode node1 = new TreeNode(12);
        TreeNode node2 = new TreeNode(10);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(21);
        TreeNode node7 = new TreeNode(5);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node4.left = node7;
        return root;
    }
}
