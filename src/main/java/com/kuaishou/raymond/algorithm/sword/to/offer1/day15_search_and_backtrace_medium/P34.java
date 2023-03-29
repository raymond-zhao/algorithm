package com.kuaishou.raymond.algorithm.sword.to.offer1.day15_search_and_backtrace_medium;

import com.kuaishou.raymond.algorithm.sword.to.offer1.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 */
public class P34 {

    public static void main(String[] args) {
        P34 p34 = new P34();
        p34.pathSum(TreeNode.defaultTree(), 30).forEach(System.out::println);
    }

    private final List<List<Integer>> data = new LinkedList<>();

    private final LinkedList<Integer> path = new LinkedList<>();

    /**
     *            3
     *           / \
     *          9  20
     *            /  \
     *           15  7
     *
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return data;
    }

    /**
     * 深度优先遍历
     * 当遍历到叶子结点时，如果 target < 0，则不符合条件。
     * 遍历到当前结点时，执行 target = target - node.val，并将当前结点 node 加入当前遍历的结果集。
     */
    private void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        target = target - root.val;

        if (target == 0 && isLeaf(root)) {
            data.add(new LinkedList<>(path));
        }

        dfs(root.left, target);
        dfs(root.right, target);
        path.removeLast();
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
