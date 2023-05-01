package com.kuaishou.raymond.algorithm.leetcode.hot100.h10backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 07:50
 * 题目：<a href="https://leetcode.cn/problems/generate-parentheses/?envType=study-plan-v2&id=top-100-liked">22. 括号生成</a>
 */
public class HOT22GenerateParentheses {

    private List<String> parentheses;

    private int parenthesesCount;

    public List<String> generateParenthesis(int n) {
        parentheses = new ArrayList<>();
        parenthesesCount = n;
        backtrack("", 0, 0);
        return parentheses;
    }

    /**
     * 深度优先搜索生成括号
     * @param currentParentheses 当前层生成的括号
     * @param leftParentheses 已经使用的左括号数量
     * @param rightParentheses 已经使用的右括号数量
     */
    private void backtrack(String currentParentheses, int leftParentheses, int rightParentheses) {
        if (leftParentheses == rightParentheses && leftParentheses == parenthesesCount) {
            parentheses.add(currentParentheses);
            return;
        }
        if (leftParentheses < rightParentheses) {
            // 如果使用的左括号数量小于右括号的数量
            return;
        }
        if (leftParentheses < parenthesesCount) {
            backtrack(currentParentheses + "(", leftParentheses + 1, rightParentheses);
        }
        if (rightParentheses < parenthesesCount) {
            backtrack(currentParentheses + ")", leftParentheses, rightParentheses + 1);
        }
    }
}
