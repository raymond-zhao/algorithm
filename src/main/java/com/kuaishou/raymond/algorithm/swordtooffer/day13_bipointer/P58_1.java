package com.kuaishou.raymond.algorithm.swordtooffer.day13_bipointer;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * --
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class P58_1 {

    public static void main(String[] args) {
        P58_1 p581 = new P58_1();
        String s = "I am a student.";
        String s2 = " Raymond is me. ";
        System.out.println("p581.reverseWords(s) = " + p581.reverseWords(s));
        System.out.println("p581.reverseWords(s2) = " + p581.reverseWords(s2));
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        s = s.trim();
        StringBuilder builder = new StringBuilder();
        int leftPointer = s.length() - 1;
        int rightPointer = s.length(); // right 指向上一次空格所在的位置
        while (leftPointer >= 0) {
            while (leftPointer >= 0 && s.charAt(leftPointer) != ' ') {
                --leftPointer;
            }
            builder.append(s.substring(leftPointer + 1, rightPointer));
            builder.append(' ');
            rightPointer = leftPointer;
            --leftPointer;
        }
        return builder.toString().trim();
    }

}
