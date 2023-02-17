package com.kuaishou.raymond.algorithm.swordtooffer.day24_math_medium_star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 和为 s 的连续正数序列
 * 给定一个正整数 target，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * target \in [1, 10^5]
 * 连续正整数序列：可以认为是公差 = 1 的等差数列的前 n（n>=2） 项和，S = n(a_1 + a_n) / 2;
 * 当 S = 9 时，n(a_1 + a_n) = 18，
 * - 当 n=2 时，a_1 + a_n = 9; a_1=9/2=4, a_n=9-a_1=9-4=5
 * - 当 n=3 时，a_1 + a_n = 6，a_1=6/3=2, a_n=6-2=4,
 * - ...
 * - 需要确定的是，n 最大应该取多少？因为 target 至少要拆分成 2 个数，n \in [2, target / 2]
 * 算法流程：
 * - 根据 2 * target=n(a_1 + a_n)，得 a_1 最大为 target / n，如果 a_1 + a_1 + 1 > target，则不成立。
 */
public class P57_2_star {

    public static void main(String[] args) {
        int target_1 = 9; // [[2,3,4], [4,5]]
        int target_2 = 15; // [[1,2,3,4,5], [4,5,6], [7,8]]
        int target_3 = 10; // [[1,2,3,4]]
        System.out.println(
                "Arrays.toString(findContinuousSequence(9)) = " + Arrays.toString(findContinuousSequence(target_1)));
    }

    /**
     * 双指针
     */
    public static int[][] findContinuousSequence(int target) {
        List<int[]> data = new ArrayList<>();
        int left = 1;
        int right = 2;
        int sum = 3;
        while (left < right) {
            if (sum == target) {
                int[] cur = new int[right - left + 1];
                for (int i = left; i < right; i++) {
                    cur[i - left] = i;
                }
                data.add(cur);
                sum = 0;
            }
            if (sum > target) {
                sum -= left;
                left++;
            }
            if (sum < target) {
                right++;
                sum += right;
            }
        }
        return data.toArray(new int[0][0]);
    }
}
