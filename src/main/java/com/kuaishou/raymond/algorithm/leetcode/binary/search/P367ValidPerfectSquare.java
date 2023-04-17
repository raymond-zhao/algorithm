package com.kuaishou.raymond.algorithm.leetcode.binary.search;

public class P367ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if ((int) middle * middle == num) {
                return true;
            } else if ((long) middle * middle < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }

}
