package com.kuaishou.raymond.algorithm.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: raymond
 * @Datetime: 2023/10/10 18:00
 * Description:
 */
public class CommonUtils {

    private CommonUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static boolean gtZero(Long id) {
        return Objects.nonNull(id) && id > 0;
    }

    public static boolean isHexColor(String color) {
        String pattern = "^#([0-9a-fA-F]{6})$";
        return StringUtils.isNotBlank(color) && color.matches(pattern);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static double countCharacters(String str) {
        int asciiCount = 0;
        int nonAsciiCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isAscii(c)) {
                asciiCount++;
            } else {
                nonAsciiCount++;
            }
        }

        // 计算总字符数，其中ASCII字符算0.5个字符，非ASCII字符算1个字符
        if (asciiCount % 2 == 0) {
            return asciiCount * 0.5 + nonAsciiCount;
        }
        return (asciiCount + 1) * 0.5 + nonAsciiCount;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static boolean isAscii(char c) {
        return c <= 127;
    }

    public static boolean lengthValid(String str, int count) {
        return StringUtils.isNotEmpty(str) && countCharacters(str) <= count;
    }

    public static String extractTopicPageId(String source) {
        // 使用正则表达式匹配并提取 "/topics/" 与 "?" 之间的内容
        Pattern pattern = Pattern.compile("/topics/(.*?)\\?");
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String extractBrandAccountUserId(String source) {
        String regex = "xhsdiscover://user/(.*)";

        // 使用正则表达式匹配并提取 "xhsdiscover://user/" 之后的内容
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }


}
