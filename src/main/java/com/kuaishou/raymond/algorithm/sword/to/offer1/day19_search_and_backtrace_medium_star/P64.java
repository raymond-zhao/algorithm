package com.kuaishou.raymond.algorithm.sword.to.offer1.day19_search_and_backtrace_medium_star;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class P64 {

    public static void main(String[] args) {
        P64 p64 = new P64();
        System.out.println("p64.sumNums(100) = " + p64.sumNums(5));
    }

    private int res = 0;

    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }

}
