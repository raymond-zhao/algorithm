package com.kuaishou.raymond.algorithm.leetcode.hot100.h6matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/spiral-matrix/?envType=study-plan-v2&id=top-100-liked">...</a>
 * 54. 螺旋矩阵
 */
public class Hot54SpiralOrderMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> data = new ArrayList<>();
        int top = 0;
        int left = 0;
        int bottom = m - 1;
        int right = n - 1;
        while (true) {
            // 从左向右
            for (int col = left; col <= right; col++) {
                data.add(matrix[top][col]);
            }
            // 自上而下
            if (++top > bottom) {
                break;
            }
            for (int row = top; row <= bottom; row++) {
                data.add(matrix[row][right]);
            }
            // 从右向左
            if (--right < left) {
                break;
            }
            for (int col = right; col >= left; col--) {
                data.add(matrix[bottom][col]);
            }
            // 自下而上
            if (--bottom < top) {
                break;
            }
            for (int row = bottom; row >= top; row--) {
                data.add(matrix[row][left]);
            }
            // 从左向右
            if (++left > right) {
                break;
            }
        }
        return data;
    }

}
