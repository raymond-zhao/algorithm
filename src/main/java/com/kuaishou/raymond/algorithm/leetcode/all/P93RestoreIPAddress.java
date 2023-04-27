package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-12 15:06
 */
public class P93RestoreIPAddress {

    public static void main(String[] args) {
        String s = "25525511135";
        P93RestoreIPAddress p93 = new P93RestoreIPAddress();
        System.out.println("p93.restoreIpAddresses(s) = " + p93.restoreIpAddresses(s));
    }

    private List<String> data;

    public List<String> restoreIpAddresses(String s) {
        data = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        if (s.length() > 12) {
            return data;
        }
        builder.append(s);
        backtrack(builder, 0, 0);

        return data;
    }

    private void backtrack(StringBuilder builder, int startIdx, int dotCount) {
        if (dotCount == 3) {
            if (isValid(builder.substring(startIdx, builder.length()))) {
                data.add(builder.toString());
            }
            return;
        }
        // 罗列所有选择
        for (int i = startIdx; i < builder.length(); i++) {
            if (isValid(builder.substring(startIdx, i + 1))) {
                builder.insert(i + 1, '.');
                backtrack(builder, i + 2, dotCount + 1);
                builder.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.length() > 1 && s.charAt(0) == '0') {
            return false;
        }
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > '9' || c < '0') {
                return false;
            }
            num = num * 10 + c - '0';
            if (num > 255) {
                return false;
            }
        }
        return true;
    }
}
