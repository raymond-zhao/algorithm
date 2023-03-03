package com.kuaishou.raymond.algorithm.swordtooffer.day20_divide_and_conquer;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，还原该二叉树并返回其根结点。
 * PS：假设输入的前序遍历和中序遍历的结果中都不包含重复的数字
 * 知识回顾：在数据结构中学习过，
 *  - 树的中序遍历 + 先序遍历可以还原一棵树；
 *  - 树的中序遍历 + 后序遍历可以还原一棵树；
 *  - 树的先序遍历 + 后序遍历不可以还原一棵树。
 * 算法思想：
 * 1. 先序遍历中，结果集中第一个结点为根结点 root，这个根结点 root，可以在先序遍历中将数组划分为 3 个区间，
 * 即 root 的左子树，根结点 root，以及 root 的右子树。
 * 2.先序遍历的第二个结点为左子树的第一个结点 root.left，这个 root.left 在中序遍历中又可以将左子树划分为 [左子树 | 根 | 右子树]；
 * 3. 采用此分支思想，构建各子树。
 * 算法流程：
 * 1. 使用 map 存储中序遍历各元素及其索引，用于在先序遍历时快速获得其索引，之后用于划分左右子树；
 * 2. 遍历先序遍历数组，找到当前元素在中序遍历数组中的位置 index，则 [0, index-1] 为左子树中的元素，[index+1, len-1] 为右子树中的元素；
 */
public class P7 {

    private int[] preorder;
    private final Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        P7 p7 = new P7();
        System.out.println("p7.buildTree(preorder, inorder) = " + p7.buildTree(preorder, inorder));
    }

    /**
     *   3
     *  / \
     * 9  20
     * /  \
     * 15  7
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        this.preorder = preorder;
        return buildTree(0, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int rootIndexInPreorder, int leftIndexInInorder, int rightIndexInInorder) {
        if (leftIndexInInorder > rightIndexInInorder) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[rootIndexInPreorder]);
        // 当前根结点在中序遍历中的位置
        int rootIndexInInorder = map.get(preorder[rootIndexInPreorder]);
        // 中序遍历区间此时被划分为 [[left, rootIndexInInorder - 1], [rootIndexInInorder], [rootIndexInInorder + 1, right]]

        // 先序遍历中的根结点后的第一个结点为其左子树的根结点
        TreeNode left = buildTree(rootIndexInPreorder + 1, leftIndexInInorder, rootIndexInInorder - 1);
        // 首先要确定根结点右子树的索引，等于当前根结点起始索引 + 左子树的结点个数
        TreeNode right = buildTree(rootIndexInPreorder + (rootIndexInInorder - leftIndexInInorder + 1), rootIndexInInorder + 1, rightIndexInInorder);

        node.left = left;
        node.right = right;

        return node;
    }
}
