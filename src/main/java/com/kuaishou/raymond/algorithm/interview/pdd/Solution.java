package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.HashMap;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-30 13:18
 */
public class Solution {

    public static void main(String[] args) {
        wordPattern("abba", "dog cat cat dog");
    }

    public static boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        int strLen = str.length();
        int idxOfStr = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (idxOfStr >= strLen) {
                // 如果指针已超出字符串长度，但是模式还未匹配完，
                return false;
            }
            int blankIdx = idxOfStr; // str 搜索空格，分割单词的指针。
            while (blankIdx < strLen && str.charAt(blankIdx) != ' ') {
                blankIdx++;
            }
            // 此时 str[i, blankIdx) 字符串中某单词
            String tmp = str.substring(idxOfStr, blankIdx);

            // 如果双射条件不成立，则匹配失败。
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }

            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);

            // 让字符串指向下一个单词的起始位置
            idxOfStr = blankIdx + 1;
        }
        return idxOfStr >= strLen;
    }

}
