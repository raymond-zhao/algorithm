package com.kuaishou.raymond.algorithm.leetcode;

/**
 * <a href="https://leetcode.cn/problems/count-and-say/">...</a>
 * 38. 外观数列
 */
public class P38FacadeArray {

    public static void main(String[] args) {
        System.out.println("countAndSay(3) = " + countAndSay(3));
    }

    /**
     * 1211
     */
    public static String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        String base = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();

            int startIdx = 0;
            int countIdx = 0;

            while (countIdx < base.length()) {
                // 统计相同字符出现的次数
                while (countIdx < base.length() && base.charAt(countIdx) == base.charAt(startIdx)) {
                    countIdx++;
                }

                // 此时，countIdx 指向的字符与 startIdx 不同，将会作为下一次搜索的起点。
                builder.append(countIdx - startIdx).append(base.charAt(startIdx));

                startIdx = countIdx;
            }
            base = builder.toString();
        }

        return base;
    }
}
