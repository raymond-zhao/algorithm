package com.kuaishou.raymond.algorithm.interview.bytedance;

import java.util.LinkedHashMap;

public class ArabicNumberToChinese {

    public static void main(String[] args) {
        System.out.println("arabicToChinese(1001) = " + arabicToChinese(1001));
        System.out.println("arabicToChinese(1101) = " + arabicToChinese(1101));
        System.out.println("arabicToChinese(10) = " + arabicToChinese(10));
    }

    private static final String[] CHINESE_DIGITS = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static final LinkedHashMap<Long, String> CHINSES_UNITS_MAP = new LinkedHashMap<>() {{
        put(100000000L, "亿");
        put(10000L, "万");
        put(1000L, "千");
        put(100L, "百");
        put(10L, "十");
        put(1L, "");
    }};

    public static String arabicToChinese(long num) {
        StringBuilder result = new StringBuilder();
        long quotient = num;

        for (Long unit : CHINSES_UNITS_MAP.keySet()) {
            long digit = quotient / unit;

            if (digit == 0 && result.length() > 0 && !result.toString().endsWith("零")) {
                result.append("零");
            }

            if (digit > 0) {
                result.append(CHINESE_DIGITS[(int) digit]);
                result.append(CHINSES_UNITS_MAP.get(unit));
            }

            quotient = quotient % unit;
        }

        if (result.toString().startsWith("一十")) {
            result.deleteCharAt(0);
        }

        return result.toString();
    }
}

