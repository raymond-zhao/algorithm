package com.kuaishou.raymond.algorithm.leetcode.all;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-13 11:33
 */
public class P7ReverseInteger {

    public static void main(String[] args) {
        System.out.println("reverse(123) = " + reverse(123));
        System.out.println("reverse(-123) = " + reverse(-123));
    }

    public static int reverse(int x) {
        int num = 0;
        while (x != 0) {
            if (Math.abs(num) > Integer.MAX_VALUE / 10) {
                return 0;
            }
            num = num * 10 + x % 10;
            x /= 10;
        }
        return num;
    }
}
