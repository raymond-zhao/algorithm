package com.kuaishou.raymond.algorithm.swordtooffer.day28_search_and_backtrace_hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个字符串，打印出这个字符串中字符的所有排列。
 */
public class P38 {

    public static String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        char[] chars = s.toCharArray();
        List<String> data = new ArrayList<>();

        return data.toArray(new String[0]);
    }
}
