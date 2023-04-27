package com.kuaishou.raymond.algorithm.leetcode.all;

import static com.kuaishou.raymond.algorithm.utils.AlgoUtils.printMatrix;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-02 20:12
 */
public class P5LongestPalindrome {

    public static void main(String[] args) {
        String s = "ababa";
        String s2 = "babad";
        String s1 = "cbbd";
        P5LongestPalindrome p5 = new P5LongestPalindrome();
        System.out.println("p5.longestPalindromeBrutalForce(s) = " + p5.longestPalindromeBrutalForce(s2));
        System.out.println("p5.longestPalindromeDP(s) = " + p5.longestPalindromeDP(s2));
    }

    /**
     * 寻找字符串 S 中最长的回文子串
     * 暴力枚举
     */
    public String longestPalindromeBrutalForce(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        // 如果 s.length() >= 2，那回文串的长度至少为 1（即为任意某单个字符）
        int maxLength = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (j - i + 1 > maxLength && isPalindrome(charArray, i, j)) {
                    maxLength = j - i + 1;
                    // 如果从 i 到 j 是回文串，那最后要截取的子串的起始位置就是 i。
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLength);
    }

    /**
     * 判断一个字符串是否是回文串
     */
    private boolean isPalindrome(char[] charArray, int i, int j) {
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
     * 中心扩散
     */
    public String longestPalindromeCenterSpread(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // 以遍历到的每一个字符为中心，向两边扩散。
            // 需要考虑回文串的长度是奇数还是偶数
            int oddLength = spreadAroundCenter(chars, i, i);
            int evenLength = spreadAroundCenter(chars, i, i + 1);

            int maxLength = Math.max(oddLength, evenLength);
            if (maxLength > end - start + 1) {
                // 如果以当前字符为中心的回文串的长度大于现有的回文串长度，更新起始索引位置为更长回文串的起始位置。
                // 以 cbbd 而言，evenLength=2=maxLength，起始索引=1，结束索引为 2。
                start = i - (maxLength - 1) / 2;
                end = i + maxLength / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 分别以 left 为左边界，right 为右边界向两边扩散。
     */
    private int spreadAroundCenter(char[] chars, int left, int right) {
        while (left >= 0 && right <= chars.length - 1 && chars[left] == chars[right]) {
            left--;
            right++;
        }
        // 跳出循环后，要么 left 或 right 越界，要么 chars[left] != chars[right]，
        // 此时 chars[left, right] 不是回文串，chars[left + 1, right - 1] 才是回文串，
        // 而这个回文串的长度 = (right - 1) - (left + 1) + 1 = right - left - 1.
        return right - left - 1;
    }

    /**
     * 动态规划
     * 状态定义：dp[i][j] 表示 s[i..j] 是否是回文串
     * 状态转移方程：
     * if s[i]==s[j], dp[i][j] = dp[i+1][j-1]，
     * if s[i]!=s[j], dp[i][j] = false;
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int maxLength = 1;
        int beginIndex = 0;
        char[] chars = s.toCharArray();

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            // 每一个单独的字符都算是回文串
            dp[i][i] = true;
        }

        // 观察状态转移方程可以发现：dp[i][j]=dp[i+1][j-1]，
        // 这个值需要从其左下角转换而来，不像 dp[i][j]=dp[i-1][j-1] 这种从其左上方转换而来，
        for (int j = 1; j < len; j++) {
            // 先填左下角，先按列填写，再按行填写。
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    // 如果头尾字符不等，则不是回文串。
                    dp[i][j] = false;
                } else {
                    // 如果首尾字符相等，并且 i..j 之间相差不足 3，也就是 s[i..j] 最多包含 3 个字符时，
                    // 去掉首尾字符后，要么剩下 1 个字符，要么剩下 0 个字符，
                    // 此时一定是回文串。
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 检查此时最新的 dp[i][j] 是否代表回文串
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    beginIndex = i;
                }
            }
        }

        printMatrix(dp);
        return s.substring(beginIndex, beginIndex + maxLength);
    }
}
