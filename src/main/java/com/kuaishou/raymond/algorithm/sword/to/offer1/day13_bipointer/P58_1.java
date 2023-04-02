package com.kuaishou.raymond.algorithm.sword.to.offer1.day13_bipointer;

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
        String s3 = "a good   example";
        System.out.println("p581.reverseWords(s) = " + p581.reverseWords(s));
        System.out.println("p581.reverseWords(s2) = " + p581.reverseWords(s2));
        System.out.println("p581.reverseWords(s3) = " + p581.reverseWords(s3));

        String s4 = "Let's take LeetCode contest";
        System.out.println("p581.reverseWords557(s4) = " + p581.reverseWords557(s4));
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
            builder.append(s, leftPointer + 1, rightPointer);
            builder.append(' ');

            while (leftPointer >= 0 && s.charAt(leftPointer) == ' ') {
                // 跳过单词之间的空格
                rightPointer = leftPointer;
                --leftPointer;
            }
        }
        return builder.toString().trim();
    }

    public String reverseWords557(String s) {
        char[] chars = s.toCharArray();

        int rightIdx = 0; // 指向当前单词的结束位置
        int leftIdx = 0; // 指向当前单词的起始位置

        while (rightIdx < chars.length) {
            while (rightIdx < chars.length && chars[rightIdx] != ' ') {
                rightIdx++;
            }
            // 此时 rightIdx 指向单词间的第一个空格，交换 chars[leftIdx, rightIdx - 1] 之间的所有字符
            swap(chars, leftIdx, rightIdx - 1);
            // rightIdx 寻找下一个单词的开头
            while (rightIdx < chars.length && chars[rightIdx] == ' ') {
                rightIdx++;
            }
            // 此时 rightIdx 指向下一个单词的开头
            leftIdx = rightIdx;
        }

        return String.valueOf(chars);
    }

    private void swap(char[] chars, int l, int r) {
        while (l < r) {
            char c = chars[l];
            chars[l] = chars[r];
            chars[r] = c;
            l++;
            r--;
        }
    }

}
