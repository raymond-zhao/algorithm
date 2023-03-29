package com.kuaishou.raymond.algorithm.sword.to.offer1.day3_string_easy;

/**
 * 旋转字符串
 * <p>
 * 如 s="abcdefg", k=2，则 s'="cdefgab"
 * 如 s="lrloseumgh", k=6，则 s'="umghlrlose"
 */
public class P58ReverseLeftWords2 {

    public String reverseLeftWords(String s, int n) {
        if (s == null || n < 0) {
            return null;
        }
        return s.substring(n) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        if (s == null || n < 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = n; i < n + s.length(); i++) {
            builder.append(s.charAt(i % s.length()));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        P58ReverseLeftWords2 p58 = new P58ReverseLeftWords2();
        String s = "abcdefg";
        String s2 = "lrloseumgh";
        System.out.println("p58.reverseLeftWords(s, 2) = " + p58.reverseLeftWords(s, 2));
        System.out.println("p58.reverseLeftWords(s, 2) = " + p58.reverseLeftWords(s2, 6));
        System.out.println("p58.reverseLeftWords(s, 2) = " + p58.reverseLeftWords2(s2, 6));
    }

}
