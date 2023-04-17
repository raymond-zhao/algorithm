package com.kuaishou.raymond.algorithm.leetcode.binary.search;

public class P69XSqrt {

    public static void main(String[] args) {
        System.out.println("mySqrt(8) = " + mySqrt(8));
    }

    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if ((long) middle * middle == x) {
                return middle;
            } else if ((long) middle * middle > x) {
                right = middle - 1;
            } else {
                // 如果无法整除，则返回最大的那个根。
                res = middle;
                left = middle + 1;
            }
        }
        return res;
    }

}
