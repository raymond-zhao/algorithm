package com.kuaishou.raymond.algorithm.sword.to.offer1.day24_math_medium_star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 和为 s 的连续正数序列
 */
public class P57_2_star {

    public static void main(String[] args) {
        int target_1 = 9; // [[2,3,4], [4,5]]
        int target_2 = 15; // [[1,2,3,4,5], [4,5,6], [7,8]]
        int target_3 = 10; // [[1,2,3,4]]
        int[][] continuousSequenceV2 = findContinuousSequenceV2(10);
        for (int[] sequence : continuousSequenceV2) {
            System.out.println("Arrays.toString(sequence) = " + Arrays.toString(sequence));
        }
    }

    public static int[][] findContinuousSequenceV2(int target) {
        List<int[]> data = new ArrayList<>();
        int left = 1;
        int right = 2;
        while (left < right) {
            int sum = (left + right) * (right - left + 1) / 2;
            if (sum == target) {
                int[] sequence = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    sequence[i - left] = i;
                }
                data.add(sequence);
                left++; // 当前序列只会存在一次，所以可以缩小窗口。
            }
            if (sum < target) {
                right++;
            } else if (sum > target) {
                left++;
            }
        }
        return data.toArray(new int[0][0]);
    }
}
