package com.kuaishou.raymond.algorithm.swordtooffer.day31_math_hard;

/**
 * 剪绳子-2：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 剪绳子-2 与 {@link com.kuaishou.raymond.algorithm.swordtooffer.day24_math_medium_star.P14_1} 不同的是，
 * 剪绳子-1 中的 n \in [2, 58]，也就是说结果最大为 549_681_949，没有超过 int 最大值，而本题中的 n \in [2, 1000]，
 * 稍微推敲一下会发现，这个值肯定是远远超出 int 的上限的，所以题目给了取模这个要求。
 * 粗略推算得知，本题的考察点是大数求余。
 * (xy) mod P = (x mod P) * (y mod P) % P
 */
public class P14_2 {

    public static void main(String[] args) {
        P14_2 p142 = new P14_2();
        System.out.println("p142.cuttingRopeV3(26) = " + p142.cuttingRopeV4(120));
    }

    private static final int MOD = (int) (1e9 + 7);

    /**
     * 循环求余
     */
    public int cuttingRopeV1(int n) {
        if (n < 4) {
            return n - 1;
        }
        long rem = 1;
        while (n > 4) {
            rem = rem * 3 % MOD;
            n = n - 3;
        }
        return (int) (rem * n % MOD);
    }

    /**
     * 循环求余
     */
    public int cuttingRopeV2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long res = 1;
        int remainder = n % 3;
        if (remainder == 1) {
            // 如果余数是 1，则需要拆出一段长为 4 的绳子，将其剪为 2X2，而不是 3X1。
            res = 4;
            n = n - 4; // 因为绳子已经被拆出 2X2，绳子行长度需要减 4.
        }
        if (remainder == 2) {
            res = 2; // 如果余数是 2，则不需要额外拆分一个 3。
            n = n - 2;
        }
        while (n >= 3) {
            res = res * 3 % MOD;
            n = n - 3;
        }
        return (int) res;
    }

    /**
     * 快速幂求余/二分求余
     */
    public int cuttingRopeV3(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int quotient = n / 3 - 1;
        int remainder = n % 3;

        long res = 1, x = 3;

        while (quotient > 0) {
            if ((quotient & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = x * x % MOD;
            quotient >>= 1;
        }

        if (remainder == 0) {
            return (int) (res * 3 % MOD);
        } else if (remainder == 1) {
            return (int) (res * 4 % MOD);
        }
        return (int) (res * 6 % MOD);
    }

    /**
     * 快速幂求余
     */
    public int cuttingRopeV4(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) (quickPow(3, quotient) % MOD);
        } else if (remainder == 1) {
            return (int) (quickPow(3, quotient - 1) * 4 % MOD);
        }
        return (int) (quickPow(3, quotient) * 2 % MOD);
    }

    public long quickPow(long base, int pow) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            pow >>= 1;
        }
        return res;
    }

    /**
     * 循环求余
     */
    public static long cyclicRemainder(int base, int pow, int mod) {
        long remainder = 1;
        for (int i = 0; i < pow; i++) {
            remainder = (remainder * base) % mod;
        }
        return remainder;
    }
}
