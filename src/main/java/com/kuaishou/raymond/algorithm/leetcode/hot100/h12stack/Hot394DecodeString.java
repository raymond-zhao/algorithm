package com.kuaishou.raymond.algorithm.leetcode.hot100.h12stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 14:49
 * 题目：<a href="https://leetcode.cn/problems/decode-string/?envType=study-plan-v2&id=top-100-liked">394. 字符串解码</a>
 */
public class Hot394DecodeString {

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Hot394DecodeString hot = new Hot394DecodeString();
        System.out.println("hot.decodeString(s) = " + hot.decodeString(s));
    }

    public String decodeString(String s) {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        Deque<Integer> counterStack = new ArrayDeque<>();
        Deque<String> resStack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                // 如果是左括号，将在此之前的数字与字符串分别入栈。
                counterStack.addLast(counter);
                counter = 0;
                resStack.addLast(builder.toString());
                builder = new StringBuilder();
            } else if (c == ']') {
                // 如果是右括号
                // 1. 将字符串 builder 拼接 counter 次，存储到临时结果 temp 中；
                // 2. 将字符串栈顶元素与 temp 拼接，赋值给字符串 builder。
                StringBuilder tmp = new StringBuilder();
                int lastCounter = counterStack.removeLast();
                tmp.append(String.valueOf(builder).repeat(Math.max(0, lastCounter)));
                builder = new StringBuilder(resStack.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                // 如果是数字，先留着。
                counter = counter * 10 + c - '0';
            } else {
                // 如果是字符，也先留着。
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
