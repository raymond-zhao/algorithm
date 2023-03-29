package com.kuaishou.raymond.algorithm.sword.to.offer1.day15_search_and_backtrace_medium;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的第 K 大结点
 */
public class P54 {

    public static void main(String[] args) {
        P54 p54 = new P54();
        System.out.println(
                "p54.kthLargest(TreeNode.defaultSearchTree(), 3) = " + p54.kthLargest(TreeNode.defaultSearchTree(), 1));
        System.out.println("p54.kthLargestV2(TreeNode.defaultSearchTree(), 1) = " + p54.kthLargestV2(TreeNode.defaultSearchTree(), 1));
    }

    private int res;
    private int k;

    public int kthLargestV2(TreeNode root, int k) {
        this.k = k;
        backOrderTraverse(root);
        return res;
    }

    private void backOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        backOrderTraverse(root.right);
        k = k - 1;
        if (k == 0) {
            res = root.val;
        }
        backOrderTraverse(root.left);
    }

    /**
     * 二叉搜索树的中序遍历结果为递增序列，寻找二叉搜索树中的第 K 大结点，
     * 其实就是寻找二叉搜索树中序遍历结果中的倒数第 K 个结点。
     */
    public int kthLargest(TreeNode root, int k) {
        middleOrderTraverse(root);
        if (k > data.size() || k == 0) {
            return -1;
        }
        return data.get(data.size() - k);
    }

    private final List<Integer> data = new ArrayList<>();

    public void middleOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        middleOrderTraverse(root.left);
        data.add(root.val);
        middleOrderTraverse(root.right);
    }
}
