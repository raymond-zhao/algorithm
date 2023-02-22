package com.kuaishou.raymond.algorithm.swordtooffer.day28_search_and_backtrace_hard;

import java.util.*;

/**
 * 输入一个字符串，打印出这个字符串中字符的所有排列。
 */
public class P38 {

    public static void main(String[] args) {
        P38 p38 = new P38();
        System.out.println("Arrays.toString(p38.permutation(\"abc\")) = " + Arrays.toString(p38.permutation("abc")));
        System.out.println("p38.permutationV2(\"abc\") = " + Arrays.toString(p38.permutationV2("abc")));
    }

    private final Set<String> data = new HashSet<>();

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        char[] chars = s.toCharArray();
        boolean[] visited = new boolean[chars.length];
        backtracking(chars, "", visited);

        return data.toArray(new String[0]);
    }

    private void backtracking(char[] chars, String temp, boolean[] visited) {
        if (chars.length == temp.length()) {
            data.add(temp);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            backtracking(chars, temp + chars[i], visited);

            visited[i] = false;
        }
    }

    private List<String> res;

    private boolean[] used;

    public String[] permutationV2(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        int len = s.length();
        used = new boolean[len];
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        // 回溯
        backtracking(chars, 0, len, new StringBuilder());

        return res.toArray(new String[0]);
    }

    private void backtracking(char[] chars, int index, int len, StringBuilder builder) {
        if (index == len) {
            res.add(builder.toString());
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i] || (i > 0 && chars[i - 1] == chars[i] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            builder.append(chars[i]);

            backtracking(chars, index + 1, len, builder);

            builder.deleteCharAt(builder.length() - 1);
            used[i] = false;
        }
    }
}
