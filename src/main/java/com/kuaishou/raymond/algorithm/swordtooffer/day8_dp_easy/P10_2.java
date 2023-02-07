package com.kuaishou.raymond.algorithm.swordtooffer.day8_dp_easy;

/**
 * 青蛙跳台阶
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上 n 级台阶总共有多少种跳法，结果对 10e9+7 取余。
 */
public class P10_2 {

    public static void main(String[] args) {
        P10_2 p102 = new P10_2();
        System.out.println("p102.numWays(10) = " + p102.numWays(10));
    }

    /**
     * 青蛙踏上第一级台阶，只有 1 种跳法。
     * 青蛙踏上第二级台阶，有 2 种跳法，即每次跳 1 级，跳 2 次，或者一次跳 2 级，跳 1 次。
     * 青蛙跳上第三级台阶时，因为青蛙每次只能跳 1 级台阶或者 2 级台阶，所以它上一次在的台阶要么是第 2 级台阶，要么是第 3 级台阶。
     * 而第一级台阶有 1 种跳法，第 2 级台阶有 2 种跳法，所以第三级台阶有 1 + 2 = 3 种跳法。
     * 同理，当跳上第 n 级台阶时，青蛙上次所处台阶要么是第 n-1 阶，要么是第 n-2 阶。
     * 0 1 2 3 4 5 6 7 8
     * 1 1 2 3 5 8 13 21
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int nMinus2 = 1;
        int nMinus1 = 1;
        int cur;
        for (int i = 2; i <= n; i++) {
            cur = (nMinus1 + nMinus2) % (int) (1e9 + 7);
            nMinus2 = nMinus1;
            nMinus1 = cur;
        }
        return nMinus1;
    }
}
