package com.kuaishou.raymond.algorithm.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-22 19:41
 */
public class ChineseNumberToArabNumber {

    public static void main(String[] args) {
        System.out.println("calculate(\"一千一\") = " + calculate("一千一百"));
    }

    /**
     * 中文数字转阿拉伯数字，确认下是不是同一个问题。
     * Input: 一千零一 Output: 1001
     */
    public static long calculate(String s) {
        s = s.replace("零", "");
        int yIndex = s.indexOf("亿");
        int wIndex = s.lastIndexOf("万");
        wIndex = wIndex < yIndex ? -1 : wIndex;
        if (yIndex != -1 && wIndex != -1) {
            return subCal(s.substring(0, yIndex)) * 1_0000_0000
                    + subCal(s.substring(yIndex + 1, wIndex)) * 1_0000
                    + subCal(s.substring(wIndex + 1));
        }
        if (yIndex != -1) {
            return subCal(s.substring(0, yIndex)) * 1_0000_0000 + subCal(s.substring(wIndex + 1));
        }
        if (wIndex != -1) {
            return subCal(s.substring(0, wIndex)) * 1_0000 + subCal(s.substring(wIndex + 1));
        }
        return subCal(s.substring(wIndex + 1));
    }

    private static long subCal(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('一', 1);
        map.put('二', 2);
        map.put('三', 3);
        map.put('四', 4);
        map.put('五', 5);
        map.put('六', 6);
        map.put('七', 7);
        map.put('八', 8);
        map.put('九', 9);

        int tenIndex = s.indexOf("十");
        int hanIndex = s.indexOf("百");
        int thouIndex = s.indexOf("千");
        long res = 0;
        if (thouIndex != -1) {
            res = res + map.get(s.charAt(thouIndex - 1)) * 1000;
        }
        if (hanIndex != -1) {
            res = res + map.get(s.charAt(hanIndex - 1)) * 100;
        }
        if (tenIndex != -1) {
            res = res + ((tenIndex - 1 < 0 ? 1 : map.get(s.charAt(tenIndex - 1))) * 10);
        }
        if (map.containsKey(s.charAt(s.length() - 1))) {
            res = res + map.get(s.charAt(s.length() - 1));
        }
        return res;
    }

}
