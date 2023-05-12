package com.kuaishou.raymond.algorithm.leetcode.hot100.h5array;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/merge-intervals/?envType=study-plan-v2&id=top-100-liked">56. 合并区间</a>
 * - 排序
 */
public class Hot56MergeIntervals {

    /**
     * 1. 将区间按照左端点排序
     * 2. 遍历排序后的区间
     */
    public int[][] merge(int[][] intervals) {
        Deque<int[]> data = new ArrayDeque<>();
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        // 对区间按左端点按升序排序，如果两个区间产生交集，只需更新右区间。
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        data.add(intervals[0]);

        for (int idx = 1; idx < intervals.length; ++idx) {
            int[] formerInterval = data.getLast();
            int[] currentInterval = intervals[idx];
            if (currentInterval[0] > formerInterval[1]) {
                // 如果当前区间与前一个区间无交集，直接加入结果集；
                data.add(currentInterval);
            } else {
                // 如果当前区间与前一个区间有交集，对两个区间取并集，然后加入结果集。
                // 因为当前区间左端点一定不必上一个区间左端点小，所以只需要更新右区间。
                // 又因为上面获取的上一个区间的引用，所以直接修改其值，而不用重新添加回去。
                formerInterval[1] = Math.max(currentInterval[1], formerInterval[1]);
            }
        }

        return data.toArray(new int[0][]);
    }

}
