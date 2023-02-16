package com.kuaishou.raymond.algorithm.swordtooffer.day20_divide_and_conquer;

/**
 * 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * ---
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * ---
 * 输入: [1,6,3,2,5]
 * 输出: false
 * ---
 * 输入: [1,3,2,6,5]
 * 输出: true
 * ---
 * 数组长度 <= 1000
 */
public class P33_star {

    public static void main(String[] args) {
        int[] postorder = {1, 6, 3, 2, 5};
        int[] postorder2 = {1, 3, 2, 6, 5};
        P33_star p33Star = new P33_star();
        System.out.println("p33.verifyPostorder(postorder) = " + p33Star.verifyPostorder(postorder));
        System.out.println("p33.verifyPostorder(postorder2) = " + p33Star.verifyPostorder(postorder2));
    }

    /**
     * 二叉树的后序遍历顺序是：左右根，将其翻转后为：根右左，
     * 翻转后的二叉搜索树特性：根结点的左子结点均大于等于根结点，根结点的右子结点均小于等于根结点。
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    /**
     * 在二叉树后序遍历序列 postorder 中搜索区间 [left, right] 内的元素是否符合二叉搜索树特性
     */
    private boolean recur(int[] postorder, int left, int right) {
        if (left >= right) {
            // 搜索区间内元素个数 <= 1，属于符合二叉搜索树特性。
            return true;
        }
        // 搜索区间内的根元素
        int rootElement = postorder[right];
        // 找到 left~right-1 范围内第一个大于 rootElement 的元素，其为 root 右子树的根结点。
        int rightRootIndex = left;
        while (postorder[rightRootIndex] < rootElement) {
            rightRootIndex++;
        }
        int middle = rightRootIndex; // 此时，区间被划分为：[[left, middle - 1], [middle, right - 1], [right]].
        // 此时，postorder[rightRootIndex] >= rootElement.
        // 从 rightRootIndex 开始向 right-1 搜索，如果搜索区间内的所有元素均大于 rootElement 则符合二叉搜索树特性。
        while (postorder[rightRootIndex] > rootElement) {
            rightRootIndex++;
        }
        // 循环结束时，如果复合二叉搜索树特性，则 rightRootIndex = right.
        return rightRootIndex == right && recur(postorder, left, middle - 1) && recur(postorder, middle, right - 1);
    }
}
