package com.kuaishou.raymond.algorithm.sword.to.offer1.day24_math_medium_star;

import java.util.ArrayList;
import java.util.List;

/**
 * 圆圈中最后剩下的数字
 */
public class P62 {

    public static void main(String[] args) {
        P62 p62 = new P62();
        System.out.println("p62.lastRemaining(5, 3) = " + p62.lastRemaining(5, 3));
        System.out.println("p62.lastRemainingV2(5, 3) = " + p62.lastRemainingV2(5, 3));
    }

    public int lastRemaining(int n, int m) {
        int data = 0;
        for (int i = 2; i <= n; i++) {
            data = (data + m) % i;
        }
        return data;
    }

    public int lastRemainingV2(int n, int m) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(i);
        }

        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            data.remove(idx);
            n--;
        }
        return data.get(0);
    }
}
