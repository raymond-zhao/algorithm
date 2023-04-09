package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P6ZigZagConversion {

    public static void main(String[] args) {
        System.out.println("convert(\"PAYPALISHIRING\", 3) = " + convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        List<StringBuilder> data = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            data.add(new StringBuilder());
        }
        char[] chars = s.toCharArray();
        int rowIdx = 0;
        int flag = -1;
        for (char c : chars) {
            data.get(rowIdx).append(c);
            if (rowIdx == 0 || rowIdx == numRows - 1) {
                flag = -flag;
            }
            rowIdx += flag;
        }
        // 转换成目标数据类型
        StringBuilder res = new StringBuilder();
        for (StringBuilder builder : data) {
            res.append(builder);
        }
        return res.toString();
    }

}
