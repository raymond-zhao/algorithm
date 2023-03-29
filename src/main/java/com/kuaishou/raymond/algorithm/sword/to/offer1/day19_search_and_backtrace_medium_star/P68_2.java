package com.kuaishou.raymond.algorithm.sword.to.offer1.day19_search_and_backtrace_medium_star;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

/**
 * 本题与 P68_1 的区别在于此树非二叉搜索树，所以并不能简单的比较结点的值来比较 root 与 p, q 的值，而是需要搜索整棵树。
 * 递归解析
 * - 终止条件：
 *  - 当越过叶子结点，则直接返回 null；
 *  - 当 root 等于 p 或 q，则直接返回 root。
 * - 递推工作
 *  - 递归左子结点，记返回值为 left；
 *  - 递归右子结点，返回值记为 right；
 * - 返回值：根据 left 与 right，可以分为四种情况。
 *  - left == null && right == null, 说明 root 的左右子结点都不包含 p，q，返回 null；
 *  - left != null && right != null, 说明 p，q 分列于 root 两侧，root 为最近公共祖先，返回 root；
 *  - left == null && right != null, 说明 p，q 均不位于 root 左子树中，返回 right，又分为两种情况。
 *   - p，q 其中一个在 root 的右子树，假设此时 right 指向 p；
 *   - p，q 两结点都在 root 的右子树，此时 right 指向最近公共祖先。
 *  - left != null && right == null, 与上一情况类似。
 */
public class P68_2 {

    public static void main(String[] args) {
        P68_2 p682 = new P68_2();
        // new 出的 Node 是没有与 root 产生联系的，是两个各自独立的零星结点，所以结果是 null。
        System.out.println("p682.lowestCommonAncestor(TreeNode.defaultTree(), new TreeNode(15), new TreeNode(7)) = "
                + p682.lowestCommonAncestor(TreeNode.defaultTree(), new TreeNode(15), new TreeNode(7)));
    }

    /**
     *            3
     *           / \
     *          9  20
     *            /  \
     *           15  7
     * 最终的返回值是树/子树的根结点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}
