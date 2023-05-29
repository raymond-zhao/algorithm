package com.kuaishou.raymond.algorithm.interview.ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-05-29 11:31
 * 给定字符串(合法字符只包括 `0,1,?` )，替换字符串中的通配符 `?` 为 `0` 或者 `1`，生成所有可能的字符串。
 */
public class StringWildcard {

    public static void main(String[] args) {
        String pattern = "1??0?101";
        String pattern2 = "1?0";
        generateAllPossibilities(pattern).forEach(System.out::println);
        generateAllPossibilities(pattern2).forEach(System.out::println);
    }

    public static List<String> generateAllPossibilities(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            return new ArrayList<>();
        }
        List<String> data = new ArrayList<>();
        generateString(pattern.toCharArray(), 0, data);
        return data;
    }

    private static void generateString(char[] charArray, int index, List<String> data) {
        if (index == charArray.length) {
            data.add(Arrays.toString(charArray));
            return;
        }

        if (charArray[index] == '?') {
            // 先把当前字符替换为 '0'，然后继续递归下一个字符。
            charArray[index] = '0';
            generateString(charArray, index + 1, data);

            // 再把当前字符替换为 '1'，然后继续递归下一个字符。
            charArray[index] = '1';
            generateString(charArray, index + 1, data);

            // 复原
            charArray[index] = '?';
        } else {
            generateString(charArray, index + 1, data);
        }
    }
}
