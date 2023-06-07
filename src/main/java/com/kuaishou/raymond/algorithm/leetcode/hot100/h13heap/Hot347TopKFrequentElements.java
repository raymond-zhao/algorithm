package com.kuaishou.raymond.algorithm.leetcode.hot100.h13heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.kuaishou.raymond.algorithm.utils.AlgoUtils;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 21:43
 * 题目：
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/?envType=study-plan-v2&id=top-100-liked">347. 前 K 个高频元素</a>
 */
public class Hot347TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = AlgoUtils.toIntArray("[1,1,1,2,2,3]");
        int[] nums2 = AlgoUtils.toIntArray("[4,1,-1,2,-1,2,3]");
        int k = 2;
        Hot347TopKFrequentElements hot = new Hot347TopKFrequentElements();
        hot.topKFrequent(nums, k);
    }

    /**
     * 小根堆：以频次为比较器对数频进行排序
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 以数频构建小根堆
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

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

        System.out.println("minHeap = " + minHeap);
        int[] data = new int[k];
        while (!minHeap.isEmpty()) {
            data[--k] = minHeap.poll();
        }
        return data;
    }

    public int[] topKFrequentII(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        // 使用 map 的 value 对 list 排序
        list.sort((a, b) -> map.get(b) - map.get(a));

        int[] data = new int[k];

        for (int idx = 0; idx < k; idx++) {
            data[idx] = list.get(idx);
        }
        return data;
    }

}
