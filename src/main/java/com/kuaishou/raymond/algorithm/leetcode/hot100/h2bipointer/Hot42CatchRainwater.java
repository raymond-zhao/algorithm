package com.kuaishou.raymond.algorithm.leetcode.hot100.h2bipointer;

import java.util.ArrayDeque;
import java.util.Deque;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * <a href="https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&id=top-100-liked">42. 接雨水</a>
 * - 双指针
 * - 动态规划
 * - 单调栈
 */
public class Hot42CatchRainwater {

    public static void main(String[] args) {
        int[] height = AlgoUtils.toIntArray("[0,1,0,2,1,0,1,3,2,1,2,1]");
        System.out.println("trapMonoStack(height) = " + trapMonoStack(height));
    }

    /**
     * 三次扫描
     */
    public static int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int data = 0;
        for (int i = 0; i < len; i++) {
            data += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        return data;
    }

    /**
     * 双指针 + 空间压缩
     */
    public int trapII(int[] height) {
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int leftIdx = 0;
        int rightIdx = height.length - 1;
        int data = 0;

        while (leftIdx < rightIdx) {
            leftMax = Math.max(leftMax, height[leftIdx]);
            rightMax = Math.max(rightMax, height[rightIdx]);
            if (leftMax <= rightMax) {
                data += leftMax - height[leftIdx++];
            } else {
                data += rightMax - height[rightIdx--];
            }
        }
        return data;
    }

    /**
     * 使用单调递减栈，存储元素索引，从栈底到栈顶递减。
     * 思路：积水只能在低洼处形成，当后面的柱子比前面的柱子低时，是接不到雨水的。
     * 所以使用单调递减栈，储存可能储水的柱子，当找到一个比前面高的柱子，也就是其后边第一个比它高的柱子时，就计算储水的高度。
     */
    public static int trapMonoStack(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int data = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > stack.peek()) {
                // 如果当前柱子比之前的某个柱子更高
                Integer poppedIdx = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 与下一个索引的值作比较
                int left = stack.peek();
                int width = i - left - 1;
                int currentHeight = Math.min(height[left], height[i]) - height[poppedIdx];
                data += width * currentHeight;
            }
            stack.push(i);
        }
        return data;
    }

}
