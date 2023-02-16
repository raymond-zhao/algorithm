package com.kuaishou.raymond.algorithm.swordtooffer.day21_bit_operation_easy_star;

/**
 * 二进制数字中 1 的个数
 */
public class P15 {

    public static void main(String[] args) {
        P15 p15 = new P15();
        System.out.println("p15.hammingWeight(11) = " + p15.hammingWeight(11));
    }

    public int hammingWeight(int n) {
        int res = 0;
        String binaryString = Integer.toBinaryString(n);
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                res++;
            }
        }
        return res;
    }

    public int hammingWeightV2(int n) {
        int res = 0;
        while (n != 0) {
            res += (n & 1);
            n = n >>> 1;
        }
        return res;
    }

    public int hammingWeightV3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
