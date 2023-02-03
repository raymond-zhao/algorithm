package com.kuaishou.raymond.algorithm.leetcode;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-02 20:12
 */
public class P5LongestPalindrome {

    public static void main(String[] args) {
        String s = "ababa";
        P5LongestPalindrome p5 = new P5LongestPalindrome();
        System.out.println("p5.longestPalindrome1(s) = " + p5.longestPalindrome1(s));
    }

    /**
     * 寻找字符串 S 中最长的回文子串
     * 暴力枚举
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLength = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (j - i + 1 > maxLength && isValidPalindrome(charArray, i, j)) {
                    maxLength = j - i + 1;
                    // 如果从 i 到 j 是回文串，那最后要截取的子串的起始位置就是 i。
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLength);
    }

    private boolean isValidPalindrome(char[] charArray, int i, int j) {
        while (i < j) {
            if (charArray[i] != charArray[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 动态规划
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLength = 1;
        int begin = 0;

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        return s.substring(begin, begin + maxLength);
    }

    /**
     * 中心扩散
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLength = 1;
        int begin = 0;

        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int oddLength = expandAroundCenter(charArray, i, i);
            int evenLength = expandAroundCenter(charArray, i, i + 1);

            int currentCharacterMaxLength = Math.max(oddLength, evenLength);
            if (currentCharacterMaxLength > maxLength) {
                maxLength = currentCharacterMaxLength;
                begin = i - (maxLength - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLength);
    }

    private int expandAroundCenter(char[] charArray, int left, int right) {
        int len = charArray.length;
        int i = left, j = right;
        while (i >= 0 && j < len) {
            if (charArray[i] == charArray[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 跳出循环时，charArray[i] != charArray[j]，
        return j - i - 1;
    }
}
