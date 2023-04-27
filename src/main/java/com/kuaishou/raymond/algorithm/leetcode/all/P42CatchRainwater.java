package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-24 14:26
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">...</a>
 * 42. 接雨水
 */
public class P42CatchRainwater {

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}; // 6
        int[] heights2 = {4, 2, 0, 3, 2, 5};

    }

    /**
     * 动态规划 + 三次扫描
     * 一次从左向右扫描，存储到 i 为止最大的 height。
     * 一次从右向左扫描，存储到 j 为止最大的 height。
     * 一次从左向右扫描，累加 Math.min(leftMax, rightMax) - height[i].
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int j = len - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }

        int data = 0;
        for (int i = 0; i < len; i++) {
            data += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return data;
    }

    /**
     * 动态规划，空间压缩，双指针。
     */
    public int trapV2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int data = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax <= rightMax) {
                data += leftMax - height[left++];
            } else {
                data += rightMax - height[right--];
            }
        }
        return data;
    }

    /**
     * 单调栈
     * 栈中存储的是元素下标，从栈底到栈顶中下标对应的元素递减。
     */
    public int trapV3(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int data = 0;
        for (int idx = 0; idx < height.length; idx++) {
            while (!stack.isEmpty() && height[idx] > height[stack.peek()]) {
                // 当当前元素大于栈顶元素时，维护单调性。
                // 出栈较小元素对应的索引
                Integer poppedIdx = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int width = idx - left - 1;
                int currentHeight = Math.min(height[left], height[idx]) - height[poppedIdx];
                data += width * currentHeight;
            }
            stack.push(idx);
        }
        return data;
    }
}
