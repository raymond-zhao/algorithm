package com.kuaishou.raymond.algorithm.swordtooffer.day26_string_medium_star;

/**
 * 面试题67. 把字符串转换成整数
 */
public class P67_star {

    public static void main(String[] args) {
        System.out.println("Integer.parseInt(\"+100\") = " + Double.valueOf("0123"));
        System.out.println("strToIntV2(\"42\") = " + strToIntV2("-hello42"));
    }

    public static int strToIntV2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int beginIndex = 0;
        // 扫描前边的所有空格
        while (beginIndex < str.length() && str.charAt(beginIndex) == ' ') {
            beginIndex++;
        }
        if (beginIndex == str.length()) {
            return 0;
        }
        int boundary = Integer.MAX_VALUE / 10;
        int sign = 1;
        int res = 0;

        if (str.charAt(beginIndex) == '-') {
            // 如果第一位是负号，改变符号位。
            sign = -1;
        }
        if (str.charAt(beginIndex) == '+' || str.charAt(beginIndex) == '-') {
            beginIndex++;
        }

        for (int i = beginIndex; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                // 如果不是数字，跳出。
                break;
            }

            if (res > boundary || (res == boundary && c > '7')) {
                // 如果再多填一位就越界，则返回整数类型界。
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + c - '0';
        }
        return sign * res;
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        int boundary = Integer.MAX_VALUE / 10;
        int sign = 1;
        int res = 0;

        int beginIndex = 1; // 假设有符号，起始位置为 1.
        if (chars[0] == '-') {
            // 如果第一位是负号，改变符号位。
            sign = -1;
        } else if (chars[0] == '+') {
            // do nothing
        } else {
            // 如果不是正负号，则可能是数字或其他字符，但无论如何，都需要从当前位置开始处理。
            beginIndex = 0;
        }

        for (int i = beginIndex; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                break;
            }

            if (res > boundary || (res == boundary && chars[i] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + chars[i] - '0';
        }

        return sign * res;
    }

}
