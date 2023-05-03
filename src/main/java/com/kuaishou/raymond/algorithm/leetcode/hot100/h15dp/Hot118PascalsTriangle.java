package com.kuaishou.raymond.algorithm.leetcode.hot100.h15dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 08:38
 * 题目：<a href="https://leetcode.cn/problems/pascals-triangle/?envType=study-plan-v2&id=top-100-liked">118. 杨辉三角</a>
 */
public class Hot118PascalsTriangle {

    public static void main(String[] args) {
        System.out.println("generate(1) = " + generate(1));
        System.out.println("generate(2) = " + generate(2));
        System.out.println("generate(3) = " + generate(3));
        System.out.println("generate(4) = " + generate(4));
        System.out.println("generate(5) = " + generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> data = new ArrayList<>();
        // 初始化第一行
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        data.add(firstRow);

        for (int row = 1; row < numRows; row++) {
            List<Integer> currentRow = new ArrayList<>();
            // 每行开头添加一个 1
            List<Integer> previousRow = data.get(row - 1);
            currentRow.add(1);

            for (int rowNo = 1; rowNo < previousRow.size(); rowNo++) {
                currentRow.add(previousRow.get(rowNo - 1) + previousRow.get(rowNo));
            }
            // 每行结尾添加一个 1
            currentRow.add(1);

            data.add(currentRow);
        }
        return data;
    }

}
