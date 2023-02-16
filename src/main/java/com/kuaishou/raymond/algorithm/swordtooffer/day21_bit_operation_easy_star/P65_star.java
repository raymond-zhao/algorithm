package com.kuaishou.raymond.algorithm.swordtooffer.day21_bit_operation_easy_star;

/**
 * 不用加减乘除做加法
 */
public class P65_star {

    public static void main(String[] args) {
        P65_star p65Star = new P65_star();
        System.out.println("p65.add(12, 13) = " + p65Star.add(12, 13));
    }

    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1; // c = 进位
            a = a ^ b; // 非进位和
            b = c; // 进位
        }
        return a;
    }

    public int addV2(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1; // 进位
            int n = a ^ b; // 非进位和
            a = n;
            b = c; // 进位
        }
        return a;
    }
}
