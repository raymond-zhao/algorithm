package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-11 15:03
 * 给定一个仅包含数字 2~9 的字符串，返回所有它能表示的字母组合。答案可以任意顺序返回。
 * 扫描数字组合，
 */
public class P17PhoneKeyboardCharCombination {

    public static void main(String[] args) {
        P17PhoneKeyboardCharCombination p17 = new P17PhoneKeyboardCharCombination();
        System.out.println("p17.combination(\"23\") = " + p17.combination("23"));
    }

    protected static final Map<Character, String> MAP = new HashMap<>();

    static {
        MAP.put('2', "abc");
        MAP.put('3', "def");
        MAP.put('4', "ghi");
        MAP.put('5', "jkl");
        MAP.put('6', "mno");
        MAP.put('7', "pqrs");
        MAP.put('8', "tuv");
        MAP.put('9', "wxyz");
        MAP.put('0', "");
        MAP.put('1', "");
    }

    private List<String> data;

    private StringBuilder builder;

    public List<String> combination(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        data = new ArrayList<>();
        builder = new StringBuilder();

        char[] digitChars = digits.toCharArray();

        backtrack(digitChars, 0);

        return data;
    }

    private void backtrack(char[] digits, int charIdx) {
        if (charIdx == digits.length) {
            data.add(builder.toString());
            return;
        }
        // 罗列所有选择
        String s = MAP.get(digits[charIdx]);
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i));
            backtrack(digits, charIdx + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
