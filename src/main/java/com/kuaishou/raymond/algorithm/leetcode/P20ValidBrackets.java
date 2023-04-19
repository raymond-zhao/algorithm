package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class P20ValidBrackets {

    public static void main(String[] args) {

    }

    protected static final Map<Character, Character> MAP = new HashMap<>();

    static {
        MAP.put(')', '(');
        MAP.put(']', '[');
        MAP.put('}', '{');
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (MAP.containsKey(c)) {
                // 如果是右括号，判断栈是否为空以及是否与栈顶元素配对。
                if (stack.isEmpty() || MAP.get(c) != stack.peek()) {
                    return false;
                }
                // 栈不为空，并且栈顶元素与当前括号配对，出栈。
                stack.pop();
            } else {
                // 如果是左括号，或者是非法字符，入栈。
                stack.push(c);;
            }
        }
        return stack.isEmpty();
    }
}
