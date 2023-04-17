package com.kuaishou.raymond.algorithm.leetcode.binary.pointer;

/**
 * https://leetcode.cn/problems/backspace-string-compare/
 */
public class P844BackspaceCompare {

    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";
        System.out.println("backspaceCompare(s, t) = " + backspaceCompare(s, t));
        String s1 = "ab##";
        String t1 = "c#d#";
        System.out.println("backspaceCompare(s1, t1) = " + backspaceCompare(s1, t1));
    }

    /**
     * 比较两个包含空格字符的字符串是否相等
     */
    public static boolean backspaceCompare(String s, String t) {
        return mapreduce(s).equals(mapreduce(t));
    }

    public static String mapreduce(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '#') {
                builder.append(c);
            } else {
                if (builder.length() > 0) {
                    builder.deleteCharAt(builder.length() - 1);
                }
            }
        }
        return builder.toString();
    }
}
