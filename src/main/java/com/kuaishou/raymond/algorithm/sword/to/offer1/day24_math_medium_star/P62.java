package com.kuaishou.raymond.algorithm.sword.to.offer1.day24_math_medium_star;

/**
 * 圆圈中最后剩下的数字
 */
public class P62 {

    public static void main(String[] args) {

    }

    public int lastRemaining(int n, int m) {
        int data = 0;
        for (int i = 2; i <=n; i++) {
            data = (data + m) % n;
        }
        return data;
    }

}
