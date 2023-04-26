package com.kuaishou.raymond.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P56MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        // 按左区间排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> data = new ArrayList<>();
        data.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] previous = data.get(data.size() - 1);
            int[] current = intervals[i];
            // 比较左端点，如果左端点在上一个区间之外，将其作为新区间添加进结果集。
            if (current[0] > previous[1]) {
                data.add(current);
            } else {
                previous[1] = Math.max(previous[1], current[1]);
            }
        }
        return data.toArray(new int[0][]);
    }

}
