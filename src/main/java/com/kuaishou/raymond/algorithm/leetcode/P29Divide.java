package com.kuaishou.raymond.algorithm.leetcode;

public class P29Divide {

    /**
     * 1. 特殊处理边界情况
     * 2. 将被除数与除数都转为负数
     *
     * @param dividend 被除数
     * @param divisor  除数
     */
    public int divide(int dividend, int divisor) {
        // 1. 如果被除数为最小值
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 2. 如果除数为最小值
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 3. 如果被除数为 0
        if (dividend == 0) {
            return 0;
        }
        // 普通情况，准备进行二分查找。
        // 记录最终的结果符号
        boolean isNegative = false;
        // 将除数与被除数均转为负数
        if (dividend > 0) {
            dividend = -dividend;
            isNegative = !isNegative;
        }
        if (divisor > 0) {
            divisor = -divisor;
            isNegative = !isNegative;
        }
        // X/Y=Z，切 X, Y 均为负数，则 ZY>=X>(Z+1)Y，我们寻找第一个使不等式成立的 Z。
        int left = 1;
        int right = Integer.MAX_VALUE;
        int data = 0;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            boolean checked = quickAdd(divisor, middle, dividend);
            if (checked) {
                data = middle;
                if (middle == Integer.MAX_VALUE) {
                    break;
                }
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return isNegative ? -data : data;
    }

    /**
     * @param y 负数 除数
     * @param z 正数 商
     * @param x 负数 被除数
     * @return 满足 z*y>=x 的最大 z
     */
    public boolean quickAdd(int y, int z, int x) {
        int result = 0;
        int add = y;

        while (z != 0) {
            // 如果 z 是奇数，单独加上 1 个 add(y)
            if ((z & 1) != 0) {
                // 需要保证 result >= x - y = x - add，否则返回 false。
                if (result < x - add) {
                    return false;
                }
                // 如果 z 是奇数，单独加上一个 add（y）
                result += add;
            }
            // 当 z>1 并且是偶数时，快速求和，每次用 add+add 实现翻倍效果，使用 z>>=1 实现 z=z/2 效果。
            if (z != 1) {
                // 需要保证 add >= x - add，否则返回 false。
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }
}
