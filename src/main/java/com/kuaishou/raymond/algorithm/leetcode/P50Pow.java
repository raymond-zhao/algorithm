package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-25 19:39
 */
public class P50Pow {

    public static void main(String[] args) {
        System.out.println("myPow(2, 4) = " + myPow(2.1, 4));
    }

    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        int pow = n;
        if (pow < 0) {
            pow = -pow;
            x = 1 / x;
        }
        double res = 1.0;
        while (pow > 0) {
            if (pow % 2 == 1) {
                res *= x;
            }
            // 底倍增
            x *= x;
            // 幂倍减
            pow >>= 1;
        }

        return res;
    }
}
