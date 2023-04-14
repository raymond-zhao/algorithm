package com.kuaishou.raymond.algorithm.leetcode;

public class P9PalindromeInteger {

    public static boolean isPalindrome(int x) {
        // 转成 String，对撞指针，太简单，略。
        return true;
    }

    public static boolean isPalindromeV2(int x) {
        if (x < 0) {
            return false;
        }
        int divisor = 1;
        while (x / divisor > 0) {
            divisor *= 10;
        }
        while (x > 0) {
            int left = x / divisor;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % divisor) / 10;
            divisor /= 100;
        }
        return true;
    }

    public static boolean isPalindromeV3(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        }
        int reversedX = 0;
        while (x > reversedX) {
            reversedX = reversedX * 10 + x % 10;
            x /= 10;
        }
        return x == reversedX || x == reversedX / 10;
    }
}
