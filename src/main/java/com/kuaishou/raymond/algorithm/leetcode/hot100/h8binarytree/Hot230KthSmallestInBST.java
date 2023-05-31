package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import java.util.ArrayList;
import java.util.List;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 21:51
 * 题目名称：230. 二叉搜索树中第K小的元素
 * 题目链接：<a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&id=top-100-liked">230. 二叉搜索树中第K小的元素</a>
 * - 中序遍历，然后返回结果集中第 k 个元素
 * - 中序遍历，当结果集中的数据个数达到 k 时，提前返回，退出后序遍历。
 * 相似问题：<a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/">剑指 Offer 54. 二叉搜索树的第k大节点</a>
 * - 中序遍历倒序（右根左）与 k-- 提前返回
 */
public class Hot230KthSmallestInBST {

    private List<Integer> data;

    private int counter;

    private int limit;

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(k)
     */
    public int kthSmallest(TreeNode root, int k) {
        data = new ArrayList<>();
        counter = 0;
        limit = k;
        inorderSearch(root);
        return data.get(k - 1);
    }

    private void inorderSearch(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderSearch(root.left);
        if (counter >= limit) {
            return;
        }
        data.add(root.val);
        counter++;
        inorderSearch(root.right);
    }

}
