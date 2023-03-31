package com.kuaishou.raymond.algorithm.interview.pdd;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-30 13:18
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 0, 1, 1, 2, 3, 5, 8
        System.out.println("solution.fibonacci(5) = " + solution.fibonacci(6));
    }

    public long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int nMinus2 = 0;
        int nMinus1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = cur;
        }
        return nMinus1;
    }

}
