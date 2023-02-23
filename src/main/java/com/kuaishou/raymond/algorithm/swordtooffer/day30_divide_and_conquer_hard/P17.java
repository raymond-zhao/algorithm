package com.kuaishou.raymond.algorithm.swordtooffer.day30_divide_and_conquer_hard;

import java.util.Arrays;

/**
 * 打印从 1 到最大的 n 位数
 * input: n=1
 * output: 1,2,3,4,5,6,7,8,9
 */
public class P17 {

    public static void main(String[] args) {
        P17 p17 = new P17();
        System.out.println("Arrays.toString(p17.printNumbers(2)) = " + Arrays.toString(p17.printNumbers(2)));
        System.out.println("Arrays.toString(p17.printNumbersV2(2)) = " + Arrays.toString(p17.printNumbersV2(2)));
    }

    public int[] printNumbers(int n) {
        int len = (int) Math.pow(10, n) - 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    private int[] data;
    private int count = 0;

    /**
     * 大数处理
     */
    public int[] printNumbersV2(int n) {
        int len = (int) Math.pow(10, n) - 1;
        data = new int[len];
        for (int digit = 1; digit <= n; digit++) { // 位数，从 1 位数到 n 位数。
            for (char c = '1'; c <= '9'; c++) {
                // 固定首位字符
                char[] nums = new char[digit];
                nums[0] = c;
                dfs(1, digit, nums);
            }
        }
        return data;
    }

    private void dfs(int currentDigit, int digit, char[] nums) {
        if (currentDigit == digit) {
            data[count++] = Integer.parseInt(String.valueOf(nums));
            return;
        }

        for (char c = '0'; c <= '9'; c++) {
            nums[currentDigit] = c;
            dfs(currentDigit + 1, digit, nums);
        }
    }
}
