package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 23:12
 * 题目：<a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/?envType=study-plan-v2&id=top-100-liked">17. 电话号码的字母组合</a>
 */
public class Hot17LetterCombinationsOfPhotoNumber {

    public static void main(String[] args) {
        String digits = "23";
        Hot17LetterCombinationsOfPhotoNumber hot = new Hot17LetterCombinationsOfPhotoNumber();
        System.out.println("hot.letterCombinations(digits) = " + hot.letterCombinations(digits));
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

    private List<String> combinations;

    private StringBuilder combination;

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        combinations = new ArrayList<>();
        combination = new StringBuilder();

        backtrack(digits.toCharArray(), 0);

        return combinations;
    }

    private void backtrack(char[] digits, int startIdx) {
        if (startIdx == digits.length) {
            combinations.add(combination.toString());
            return;
        }
        String alphabets = MAP.get(digits[startIdx]);
        for (int i = 0; i < alphabets.length(); i++) {
            combination.append(alphabets.charAt(i));
            // digits 当前数字所代表的字母与下一个数字所代表的字母进行组合
            backtrack(digits, startIdx + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
