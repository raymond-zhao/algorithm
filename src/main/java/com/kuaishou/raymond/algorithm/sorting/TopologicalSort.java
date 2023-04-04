package com.kuaishou.raymond.algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-03 17:42
 */
public class TopologicalSort {

    // 定义一个图形
    private static final Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        // 添加节点和边
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4, 5));
        graph.put(3, Arrays.asList(5, 6));
        graph.put(4, List.of(7));
        graph.put(5, List.of(7));
        graph.put(6, List.of(7));
        graph.put(7, new ArrayList<>());

        // 进行拓扑排序
        List<Integer> sorted = topologicalSort(graph);

        // 输出排序结果
        System.out.println(sorted);
    }

    public static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        // 计算每个节点的入度
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int node = entry.getKey();
            indegrees.putIfAbsent(node, 0);
            for (int neighbor : entry.getValue()) {
                indegrees.put(neighbor, indegrees.getOrDefault(neighbor, 0) + 1);
            }
        }

        // 将所有入度为 0 的节点加入队列
        for (int node : indegrees.keySet()) {
            if (indegrees.get(node) == 0) {
                queue.add(node);
            }
        }

        // 依次处理队列中的节点
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            // 将所有邻居节点的入度减 1
            for (int neighbor : graph.get(curr)) {
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // 如果排序后的节点数小于图形中的节点数，说明存在环，返回空列表
        return result.size() == graph.size() ? result : new ArrayList<>();
    }

}
