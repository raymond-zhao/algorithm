package com.kuaishou.raymond.algorithm.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-03 17:41
 */
public class SimpleGraph {

    // 定义一个图形
    private static Map<Character, Set<Character>> graph = new HashMap<>();

    public static void main(String[] args) {
        // 添加节点和边
        graph.put('A', new HashSet<>(Arrays.asList('B', 'C')));
        graph.put('B', new HashSet<>(Arrays.asList('A', 'D', 'E')));
        graph.put('C', new HashSet<>(Arrays.asList('A', 'F')));
        graph.put('D', new HashSet<>(Arrays.asList('B')));
        graph.put('E', new HashSet<>(Arrays.asList('B')));
        graph.put('F', new HashSet<>(Arrays.asList('C')));

        // 从节点 A 开始进行 BFS
        bfs('A');

        // 从节点 A 开始进行 DFS
        dfs('A', new HashSet<>());
    }

    public static void bfs(Character start) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Character curr = queue.poll();
            System.out.print(curr + " ");

            // 获取当前节点的所有邻居节点
            Set<Character> neighbors = graph.get(curr);
            if (neighbors == null) continue;

            for (Character neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void dfs(Character curr, Set<Character> visited) {
        visited.add(curr);
        System.out.print(curr + " ");

        // 获取当前节点的所有邻居节点
        Set<Character> neighbors = graph.get(curr);
        if (neighbors == null) return;

        for (Character neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

}
