package com.kuaishou.raymond.algorithm.sword.to.offer2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-03-29 10:13
 * 后缀表达式
 * 根据逆波兰表示法，求该后缀表达式的计算结果。
 * 有效的运算符包括 `+, -, *, /`，每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 */
public class P36EvalRPN {

    public static void main(String[] args) {
        // tokens[i] 要么是一个运算符，要么是一个范围在 [-200, 200] 之间整数。
        String[] tokens1 = {"2", "1", "+", "3", "*"}; // 9
        String[] tokens2 = {"4", "13", "5", "/", "+"}; // 6
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}; // 22
        P36EvalRPN evalRPN = new P36EvalRPN();
        System.out.println("evalRPN.evalRPN(tokens1) = " + evalRPN.evalRPN(tokens1));
        System.out.println("evalRPN.evalRPN(tokens2) = " + evalRPN.evalRPN(tokens2));
        System.out.println("evalRPN.evalRPN(tokens3) = " + evalRPN.evalRPN(tokens3));
    }

    /**
     * 计算后缀表达式
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                // 如果是数字，入栈等待计算。
                stack.push(Integer.parseInt(token));
            } else {
                // 如果是运算符，取出两个数字，与运算后操作后重新入栈。
                Integer rightNumber = stack.pop();
                Integer leftNumber = stack.pop();
                if ("+".equals(token)) {
                    stack.push(leftNumber + rightNumber);
                } else if ("-".equals(token)) {
                    stack.push(leftNumber - rightNumber);
                } else if ("*".equals(token)) {
                    stack.push(leftNumber * rightNumber);
                } else if ("/".equals(token)) {
                    stack.push(leftNumber / rightNumber);
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
