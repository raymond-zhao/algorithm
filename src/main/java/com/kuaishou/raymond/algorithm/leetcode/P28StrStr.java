package com.kuaishou.raymond.algorithm.leetcode;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class P28StrStr {

    public static void main(String[] args) {
        String haystack = "leetcode";
        String needle = "cod";
        System.out.println("strStr(haystack, needle) = " + strStr(haystack, needle));
        System.out.println("strStrBruteForce(haystack, needle) = " + strStrBruteForce(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static int strStrBruteForce(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP
     * <a href="https://www.nayuki.io/page/knuth-morris-pratt-string-matching">...</a>
     * @param haystack 原字符串
     * @param needle 模式
     * @return 模式匹配开始时的索引
     */
    public static int KMP(String haystack, String needle) {
        int[] lsp = computeLspTable(needle);
        int j = 0;  // Number of chars matched in pattern
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                // Fall back in the pattern
                j = lsp[j - 1];  // Strictly decreasing
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++; // Next char matched, increment position
                if (j == needle.length()) {
                    return i - (j - 1);
                }
            }
        }
        return -1;  // Not found
    }

    /**
     * longest suffix-prefix
     * @param pattern pattern
     * @return longest suffix-prefix
     */
    private static int[] computeLspTable(String pattern) {
        int[] lsp = new int[pattern.length()];
        lsp[0] = 0;  // Base case
        for (int i = 1; i < pattern.length(); i++) {
            // Start by assuming we're extending the previous LSP
            int j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = lsp[j - 1];
            if (pattern.charAt(i) == pattern.charAt(j))
                j++;
            lsp[i] = j;
        }
        return lsp;
    }

    public int strStrKMP(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }

        int[] lsp = computeLSP(needle);

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = lsp[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] computeLSP(String needle) {
        int m = needle.length();
        int[] lsp = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = lsp[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            lsp[i] = j;
        }
        return lsp;
    }
}
