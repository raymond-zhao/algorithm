package com.kuaishou.raymond.algorithm.swordtooffer.day10_dp_medium_star;

/**
 * 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 0 <= num < 2^31
 */
public class P46 {

    public static void main(String[] args) {
        int num = 12258;
        P46 p46 = new P46();
        System.out.println("p46.translateNum(num) = " + p46.translateNum(26));
    }

    /**
     * 设 dp[i] 表示到数字 i 为止的翻译方法
     * 数字 x = x_1x_2x_3..x_{i-1}x_{i}...x_n
     * 则 x_{i-2}x_{i-1} 在 10~25 之间时，dp[i] = dp[i-1] + dp[i-1];
     * 当 x_{i-2}x_{i-1} 在 00~09 或 26~99 之间时，无法整体翻译，dp[i] = dp[i-1];
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length() + 1]; // n+1
        dp[0] = 1; // 当有 2 个数字时，dp[2] = dp[0] + dp[1] = 2，而 dp[1]=1，所以 dp[0]=1。
        dp[1] = 1; // 只有 1 个数字时，只有 1 种翻译方式。
        for (int i = 2; i <= s.length(); i++) { // <=
            String digitStr = s.substring(i - 2, i);
            int digit = Integer.parseInt(digitStr);
            if (digit >= 10 && digit <= 25) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}
