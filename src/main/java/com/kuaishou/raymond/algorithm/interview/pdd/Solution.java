package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-30 13:18
 */
public class Solution {

    public static void main(String[] args) {
        isValid("([}}])");
    }

    private static final Map<Character, Character> MAP = new HashMap<>();

    static {
        MAP.put(')', '(');
        MAP.put(']', '[');
        MAP.put('}', '{');
    }

    public static boolean isValid(String s) {

        if (s.length() % 2 == 1) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (MAP.containsKey(ch)) {
                // 如果是右括号
                if (stack.isEmpty() || stack.peek() != MAP.get(ch)) {
                    // 如果栈内没有左括号，或者栈顶括号与当前括号不匹配。
                    return false;
                }
                stack.pop();
            } else {
                // 如果是左括号，入栈。
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

}
