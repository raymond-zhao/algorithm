package com.kuaishou.raymond.algorithm.swordtooffer.day7_search_and_backtrace;

import com.kuaishou.raymond.algorithm.swordtooffer.TreeNode;

/**
 * 树的子结构: 树 B 和 A 的子结构具有相同的层次结构与值
 * 输入两棵树 A 和 B，判断 B 是不是 A 的子结构。注意：空树不是任何树的子结构。
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 20
 *   \
 *    7
 */
public class P26 {

    public static void main(String[] args) {
        P26 p26 = new P26();
        TreeNode node1 = new TreeNode(20);
        TreeNode node2 = new TreeNode(7);
        node1.right = node2;
        System.out.println(
                "p26.isSubStructure(TreeNode.defaultTree(), node1) = " + p26.isSubStructure(TreeNode.defaultTree(),
                        node1));
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 是子结构的条件：B 要么是 A 根节点开始的子结构，要么是 A 的左子树的子结构，要么是 B 的右子树的子结构，三者满足其一即为子结构。
        return (A != null && B != null) && (isSameStructure(A, B) ||  isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean isSameStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            // 如果 b 走到头，说明 B 目前为止的所有结点都在 A 的子结构中。
            return true;
        }
        if (a == null) {
            // 如果 a 走到头，b 还没走到头，那么 B 一定不是 A 的子树。
            return false;
        }
        return a.val == b.val && isSameStructure(a.left, b.left) && isSameStructure(a.right, b.right);
    }
}
