package com.kuaishou.raymond.algorithm.leetcode.hot100.h13heap;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

import java.util.*;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 21:43
 * 题目：<a href="https://leetcode.cn/problems/top-k-frequent-elements/?envType=study-plan-v2&id=top-100-liked">347. 前 K 个高频元素</a>
 */
public class Hot347TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,1,1,2,2,3]");
        int[] nums2 = AlgoUtils.toIntArray("[4,1,-1,2,-1,2,3]");
        int k =4;
        Hot347TopKFrequentElements hot = new Hot347TopKFrequentElements();
        hot.topKFrequent(nums2, k);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (int key : map.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(key);
            } else {
                Integer heap = minHeap.peek();
                if (map.get(key) > map.get(heap)) {
                    minHeap.poll();
                    minHeap.offer(key);
                }
            }
        }

        int[] data = new int[k];
        while (!minHeap.isEmpty()) {
            data[--k] = minHeap.poll();
        }
        return data;
    }

}
