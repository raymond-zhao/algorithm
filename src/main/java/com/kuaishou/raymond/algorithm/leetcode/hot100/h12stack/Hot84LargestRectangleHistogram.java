package com.kuaishou.raymond.algorithm.leetcode.hot100.h12stack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 17:36
 * 题目：<a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/?envType=study-plan-v2&id=top-100-liked">84. 柱状图中最大的矩形</a>
 * 相似题目：{@link com.kuaishou.raymond.algorithm.leetcode.hot100.h2bipointer.Hot42CatchRainwater}
 * - 单调递减栈：求下一个更大元素
 * - 单调递增栈：求下一个更小元素
 */
public class Hot84LargestRectangleHistogram {

    public static void main(String[] args) {
        int[] heights = AlgoUtils.toIntArray("[2,1,5,6,2,3]");
        Hot84LargestRectangleHistogram hot = new Hot84LargestRectangleHistogram();
        System.out.println("hot.largestRectangleArea(heights) = " + hot.largestRectangleArea(heights));
    }

    public int largestRectangleAreaII(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体（哨兵）。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        int area = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                area = Math.max(area, (i - stack.element() - 1) * h);
            }
            stack.push(i);
        }

        return area;
    }

    /**
     * 使用单调递增栈来维护一个递增的高度序列，在遍历每个柱子的高度时，
     * 可以通过栈中元素找到左边界和右边界，从而计算出当前柱子高度所能组成的最大矩形面积。
     */
    public int largestRectangleArea(int[] heights) {
        // 递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        int len = heights.length;

        // left[i] 表示 i 左侧第一个严格小于 height[i] 的索引，从后先前扫描
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // 使用递增栈，寻找下一个比栈顶元素小的数字。
                stack.pop();
            }
            // 此时栈中元素均小于 height[i]，栈顶元素为距离 i 最近的严格小于 height[i] 的值.
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // right[i] 表示其右侧第一个小于 height[i] 的索引，从前向后扫描。
        int[] right = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // 使用递增栈，寻找下一个比栈顶元素小的数字。
                stack.pop();
            }
            // 此时栈顶元素为严格小于 height[i] 的值
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }

        int area = 0;
        for (int i = 0; i < len; i++) {
            int width = right[i] - left[i] - 1;
            area = Math.max(area, width * heights[i]);
        }
        return area;
    }

    /**
     * 暴力解法：O(n^2)
     * 超时，不能 AC。
     */
    public int largestRectangleAreaBF(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                // 向左寻找到第一个严格小于 height[i] 的值
                --left;
            }
            int right = i;
            while (right < heights.length && heights[right] >= heights[i]) {
                // 向右寻找到第一个严格小于 height[i] 的值
                ++right;
            }
            int width = right - left - 1;
            area = Math.max(width * heights[i], area);
        }

        return area;
    }

}
