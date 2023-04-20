package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/
 */
public class P22GenerateParentheses {

    public static void main(String[] args) {
        P22GenerateParentheses p22 = new P22GenerateParentheses();
        System.out.println("p22.generateParenthesis(2) = " + p22.generateParenthesis(2));
    }

    private List<String> data;

    private int parenthesesCount;

    public List<String> generateParenthesis(int n) {
        this.data = new ArrayList<>();
        this.parenthesesCount = n;

        dfs("", 0, 0);

        return data;
    }

    /**
     * 深度优先搜索生成括号
     * @param parentheses 当前流程生成的括号
     * @param leftParenthese 已经使用的左括号数量
     * @param rightParenthese 已经使用的右括号数量
     * @param parenthesesCount 总共可以使用的左括号数量
     */
    private void dfs(String parentheses, int leftParenthese, int rightParenthese) {
        if (leftParenthese == parenthesesCount && rightParenthese == parenthesesCount) {
            // 如果左括号使用完，并且右括号使用完。
            data.add(parentheses);
            return;
        }
        // 剪枝，当左括号的使用数量小于右括号的使用数量时，提前返回。
        if (leftParenthese < rightParenthese) {
            return;
        }
        // 如果左括号还没有使用完，继续搜索。
        if (leftParenthese < parenthesesCount) {
            dfs(parentheses + "(", leftParenthese + 1, rightParenthese);
        }
        // 如果右括号还没有使用完
        if (rightParenthese < parenthesesCount) {
            dfs(parentheses + ")", leftParenthese, rightParenthese + 1);
        }
    }

}
