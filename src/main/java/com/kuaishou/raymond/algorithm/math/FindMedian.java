package com.kuaishou.raymond.algorithm.math;

import java.util.Objects;

import org.joda.time.DateTime;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-13 10:18
 */
@Slf4j
public class FindMedian {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 1, 5, 2, 7, 5, 3, 8, 9};
        int[] array = nums1;
        log.info("min = {}", minimum(array));
        log.info("max = {}", maximum(array));
        log.info("min and max = {}", findMinimumAndMaximum(array));
        log.info("2nd large = {}", find2ndLarge(array));

        long dateTime = new DateTime(System.currentTimeMillis()).withTimeAtStartOfDay().plusDays(1).toInstant().getMillis();
        log.info("dateTime = {}", dateTime);
    }

    public static int minimum(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return Integer.MAX_VALUE;
        }

        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }

        return min;
    }

    public static int maximum(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }

        return max;
    }

    /**
     * 最多比较 3 * (n / 2) 次，而不是 2(n - 1) 次。
     */
    public static int[] findMinimumAndMaximum(int[] array) {
        if (Objects.isNull(array) || array.length == 0 || array.length == 1) {
            return array;
        }

        int length = array.length;
        int minimum;
        int maximum;
        if ((length & 1) == 0) {
            // 偶数，比较前两个值，初始化最小最大值。
            minimum = Math.min(array[0], array[1]);
            maximum = Math.max(array[0], array[1]);

            for (int i = 2; i < length - 1; i += 2) {
                int l = array[i];
                int r = array[i + 1];
                boolean lGTR = l >= r;

                if (lGTR) {
                    maximum = Math.max(maximum, l);
                    minimum = Math.min(minimum, r);
                } else {
                    maximum = Math.max(maximum, r);
                    minimum = Math.min(minimum, l);
                }
            }
        } else {
            // 奇数
            minimum = array[0];
            maximum = array[0];
            for (int i = 1; i < length - 1; i += 2) {
                int l = array[i];
                int r = array[i + 1];
                boolean lGTR = l >= r;

                if (lGTR) {
                    maximum = Math.max(maximum, l);
                    minimum = Math.min(minimum, r);
                } else {
                    maximum = Math.max(maximum, r);
                    minimum = Math.min(minimum, l);
                }
            }
        }
        return new int[] {minimum, maximum};
    }

    public static int find2ndLarge(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = array[0];
        int secondLarge = Integer.MIN_VALUE;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                secondLarge = max;
                max = array[i];
            }
        }

        return secondLarge;
    }
}
