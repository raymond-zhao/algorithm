package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 12:14
 * 题目：<a href="https://leetcode.cn/problems/word-break/?envType=study-plan-v2&id=top-100-liked">139. 单词拆分</a>
 * - Medium
 */
public class Hot139WordBreak {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println("wordBreak(s, wordDict) = " + wordBreak(s, wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        // dp[i]，表示字符 s[0..i-1] 是否能被拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String substring = s.substring(j, i);
                if (dp[j] && set.contains(substring)) {
                    // 如果 0..j-1 之间的字符在字典内，并且 j..i-1 之间的字符也在字典内，则字符串可被分割。
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
