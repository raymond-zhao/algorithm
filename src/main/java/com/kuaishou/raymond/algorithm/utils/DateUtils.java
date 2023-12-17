package com.kuaishou.raymond.algorithm.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author: raymond
 * @Datetime: 2023/11/27 14:52
 * @Description:
 */
public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private static final String E_8 = "+8";

    /**
     * 将毫秒时间戳转成 LocalDateTime
     * @param milliseconds 毫秒时间
     */
    public static LocalDateTime toLocalDateTime(long milliseconds) {
        return new Date(milliseconds).toInstant().atOffset(ZoneOffset.of(E_8)).toLocalDateTime();
    }

    /**
     * 检查某些日期中是否有任意一个日期早于某指定日期
     * @param targetTime 特定日期
     * @param dateTimes 要判断的某些日期
     * @return true if 某日期早于特定日期，反之为 false.
     */
    public static boolean anyBefore(LocalDateTime targetTime, LocalDateTime... dateTimes) {
        for (LocalDateTime dateTime : dateTimes) {
            if (dateTime.isBefore(targetTime)) {
                return true;
            }
        }
        return false;
    }

    public static long getDuration(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit chronoUnit) {
        return chronoUnit.between(startTime, endTime);
    }

    /**
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 两个日期之间的间隔
     */
    public static Duration getDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    /**
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 两个日期之间的天数
     */
    public static long betweenDays(LocalDateTime startTime, LocalDateTime endTime) {
        return getDuration(startTime, endTime).toDays();
    }

    /**
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 两个日期之间的秒数
     */
    public static long betweenMillis(LocalDateTime startTime, LocalDateTime endTime) {
        return getDuration(startTime, endTime).toMillis();
    }

    /**
     * 计算下一年的开始时间
     */
    public static LocalDateTime startOfNextYear() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.plusYears(1).withMonth(Month.JANUARY.getValue()).withDayOfMonth(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 计算下一年的结束时间
     */
    public static LocalDateTime endOfNextYear() {
        LocalDateTime nextYearStart = startOfNextYear();
        return nextYearStart.withMonth(Month.DECEMBER.getValue()).withDayOfMonth(31)
                .withHour(23).withMinute(59).withSecond(59);
    }

    /**
     * 判断给定日期是不是当月的第一天
     * @param localDateTime 给定日期
     * @return true if first day else false
     */
    public static boolean isFirstDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.getDayOfMonth() == 1;
    }

    /**
     * @param localDateTime 给定日期
     * @return 获取给定日期月末
     */
    public static LocalDateTime lastDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 判断给定日期是不是当月的最后一天
     * @param localDateTime 给定日期
     * @return true if last day else false
     */
    public static boolean isLastDayOfMonth(LocalDateTime localDateTime) {
        LocalDateTime lastDayOfMonth = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return localDateTime.getDayOfMonth() == lastDayOfMonth.getDayOfMonth();
    }

    /**
     * @param localDateTime 给定日期
     * @return 获取给定日期次月前一天
     */
    public static LocalDateTime previousDayOfNextMonth(LocalDateTime localDateTime) {
        return localDateTime.plusMonths(1).minusDays(1);
    }

    /**
     * 判断给定日期是不是次月前一天
     */
    public static boolean isNextMonthPreviousDay(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime previousDayOfNextMonth = previousDayOfNextMonth(startTime);
        return previousDayOfNextMonth.isEqual(endTime);
    }

}
