package com.kuaishou.raymond.algorithm.leetcode.hot100.h14greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: raymond
 * CreateTime: 2023/5/3 00:00
 * 题目：<a href="https://leetcode.cn/problems/partition-labels/?envType=study-plan-v2&id=top-100-liked">763. 划分字母区间</a>
 */
public class Hot763PartitionLabels {

    /**
     * 本题第二个循环中的逻辑与{@link Hot45JumpGameII}有些相似
     */
    public List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 存储某个字符最后一次出现的位置
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        int firstAppearence = 0;
        int lastAppearence = 0;

        List<Integer> partition = new ArrayList<>();
        for (int currentPosition = 0; currentPosition < s.length(); currentPosition++) {
            // 当前字符可以出现的最远位置（类比可以从当前位置跳跃到的最远距离）
            int lastPosition = lastIndex[s.charAt(currentPosition) - 'a'];
            // 更新区间内所有字符最后出现的位置
            lastAppearence = Math.max(lastAppearence, lastPosition);
            if (currentPosition == lastAppearence) {
                // 如果当前位置已经达到了之前能达到的最远距离，进行一次分割。
                partition.add(lastAppearence - firstAppearence + 1);
                // 更新下一次搜索的起始位置
                firstAppearence = lastAppearence + 1;
            }
        }
        return partition;
    }
}
