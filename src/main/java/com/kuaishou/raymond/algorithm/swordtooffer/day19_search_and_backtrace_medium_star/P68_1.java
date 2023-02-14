package com.kuaishou.raymond.algorithm.swordtooffer.day19_search_and_backtrace_medium_star;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * 祖先的定义：若结点 p 在结点 root 的左右子树中，或 p = root，则称 root 是 p 的祖先。
 * 最近公共祖先的定义：设结点 root 为结点 p,q 的某公共祖先，若其左子结点 root.left 和右子结点 root.right
 * 都不是 p,q 的公共祖先，则称 root 是最近的公共祖先。
 * 根据以上定义，若 root 是 p，q 的最近公共祖先，则只可能为以下情况之一：
 * - p 与 q 分别位于 root 的左右子树之中；
 * - p = root，且 q 在 root 的左子树或右子树中；
 * - q = root，且 p 在 root 的左子树或右子树中；
 * 结合题目中给出的 (1). 树为二叉搜索树与 (2). 树的所有结点的值都是唯一的，可以方便地判断：
 * - 若 root.val < p.val，则 p 在 root 的右子树中；
 * - 若 root.val > p.val，则 p 在 root 的左子树中；
 * - 若 root.val = p.val，则 p 和 root 指向同一结点。
 * 迭代算法基本思路：
 * 1. 循环搜索，当 root 为空时跳出
     * 1. 若 p, q 都在 root 的右子树中，则到 root.right 中迭代；
     * 2. 若 p, q 都在 root 的左子树中，则到 roo.left 中迭代；
     * 3. 否则，p，q 分列于 root 两侧或者 p，q 二者之一等于 root，都说明已经找到**最近公共祖先**。
 * 2. 返回最近公共祖先 root。
 */
public class P68_1 {

    public static void main(String[] args) {
        P68_1 p681 = new P68_1();

    }

    /**
     * 迭代
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 递归
     */
    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorV2(root.left, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorV2(root.right, p, q);
        }
        return root;
    }

}
