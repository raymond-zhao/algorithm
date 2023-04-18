package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class P11MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int data = -1;
        int leftIdx = 0;
        int rightIdx = height.length - 1;
        while (leftIdx <= rightIdx) {
            data = Math.max(data, height[leftIdx] <= height[rightIdx] ? (rightIdx - leftIdx) * height[leftIdx++] : (rightIdx - leftIdx) * height[rightIdx--]);
        }
        return data;
    }

}
