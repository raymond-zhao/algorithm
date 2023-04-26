package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P54SpiralOrderMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        List<Integer> data = new ArrayList<>();
        while (true) {
            for (int i = left; i <= right; i++) {
                // 从左向右
                data.add(matrix[top][i]);
            }
            if (++top > bottom) {
                break;
            }
            // 从上往下
            for (int i = top; i <= bottom; i++) {
                data.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }
            // 从右向左
            for (int i = right; i >= left; i--) {
                data.add(matrix[bottom][i]);
            }
            if (--bottom < top) {
                break;
            }
            // 从下往上
            for (int i = bottom; i >= top; i--) {
                data.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return data;
    }

}
