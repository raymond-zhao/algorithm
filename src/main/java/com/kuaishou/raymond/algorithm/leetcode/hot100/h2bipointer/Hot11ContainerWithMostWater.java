package com.kuaishou.raymond.algorithm.leetcode.hot100.h2bipointer;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-27 13:03
 * <a href="https://leetcode.cn/problems/container-with-most-water/">11. 盛最多水的容器</a>
 * - 双指针
 * - 相似题目: {@link Hot42CatchRainwater}
 */
public class Hot11ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = AlgoUtils.toIntArray("[1,8,6,2,5,4,8,3,7]");
        System.out.println("maxArea(height) = " + maxArea(height));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // (right-left) 必须放在乘式的左侧，放在右侧的话，left++ 与 right-- 会改变其值。
            maxArea = Math.max(maxArea, height[left] <= height[right] ? (right - left) * height[left++] : (right - left) * height[right--]);
        }

        return maxArea;
    }

}
