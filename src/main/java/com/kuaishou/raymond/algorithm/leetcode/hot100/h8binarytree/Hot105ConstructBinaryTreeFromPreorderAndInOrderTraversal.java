package com.kuaishou.raymond.algorithm.leetcode.hot100.h8binarytree;

import com.kuaishou.raymond.algorithm.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 23:22
 * 题目：<a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&id=top-100-liked">105. 从前序与中序遍历序列构造二叉树</a>
 */
public class Hot105ConstructBinaryTreeFromPreorderAndInOrderTraversal {

    /**
     * 存储中序遍历元素与索引
     * Key: 值
     * Value：值在中序遍历中的索引
     */
    private static final Map<Integer, Integer> MAP = new HashMap<>();

    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            MAP.put(inorder[i], i);
        }
        this.preorder = preorder;
        return buildTree(0, 0, inorder.length - 1);
    }

    /**
     * @param rootIdxInPreorder 子树根节点在先序遍历中的索引
     * @param inLeft 中序数组中左区间边界
     * @param inRight 中序数组中右区间边界
     * @return 结合以上三参数构造出的子树根节点
     */
    private TreeNode buildTree(int rootIdxInPreorder, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        // 先序遍历中的第一个节点为根节点
        Integer rootIdxInInorder = MAP.get(preorder[rootIdxInPreorder]);
        // 中序遍历被划分为 [left, rootIdx-1], [rootIdx], [rootIdx+1];
        TreeNode root = new TreeNode(preorder[rootIdxInPreorder]);
        // 前半段区间在先序遍历中的起始位置=rootIdxInPreorder+1（先序遍历中根节点之后的第一个元素是左子树的开始位置）
        root.left = buildTree(rootIdxInPreorder + 1, inLeft,  rootIdxInInorder - 1);
        // 后半段区间在先序遍历中的起始位置=rootIdxInPreorder+1+左子树所占用的节点个数
        root.right = buildTree(rootIdxInPreorder + 1 + (rootIdxInInorder - inLeft), rootIdxInInorder + 1, inRight);
        return root;
    }

}
