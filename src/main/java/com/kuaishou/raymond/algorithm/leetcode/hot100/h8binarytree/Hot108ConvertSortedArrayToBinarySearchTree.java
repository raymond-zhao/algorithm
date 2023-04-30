package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 21:13
 * 题目名称：108. 将有序数组转换为二叉搜索树
 * 题目链接：<a href="https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 相似问题：
 * - 有序链表转二叉搜索树（小红书四年社招一面）：{@link com.kuaishou.raymond.algorithm.leetcode.all.P109SortedListToBST}
 */
public class Hot108ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = sortedArrayToBST(nums, left, middle - 1);
        root.right = sortedArrayToBST(nums, middle + 1, right);
        return root;
    }

}
