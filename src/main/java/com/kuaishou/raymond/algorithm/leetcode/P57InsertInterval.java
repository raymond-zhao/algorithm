package com.kuaishou.raymond.algorithm.leetcode;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-26 19:02
 */
public class P57InsertInterval {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] ints = insert(intervals2, newInterval2);
        AlgoUtils.printMatrix(ints);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        List<int[]> data = new ArrayList<>();
        boolean inserted = false;
        for (int[] currentInterval : intervals) {
            if (right < currentInterval[0]) {
                if (!inserted) {
                    data.add(new int[]{left, right});
                    inserted = true;
                }
                data.add(currentInterval);
            } else if (left > currentInterval[1]) {
                data.add(currentInterval);
            } else {
                left = Math.min(currentInterval[0], left);
                right = Math.max(currentInterval[1], right);
            }
        }
        if (!inserted) {
            data.add(new int[]{left, right});
        }
        return data.toArray(new int[0][]);
    }
}
