package com.kuaishou.raymond.algorithm.leetcode.hot100.h9graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Author: raymond
 * CreateTime: 2023/5/1 19:14
 * 题目：<a href="https://leetcode.cn/problems/course-schedule/?envType=study-plan-v2&id=top-100-liked">207. 课程表</a>
 * 相似题目：<a href="https://leetcode.cn/problems/course-schedule-ii/">210. 课程表 II</a>
 * 知识点：拓扑排序
 */
public class Hot207CoursesSchedule {

    /**
     * 广度优先搜索+拓扑排序
     * 时间复杂度：O(E+V)
     * 空间复杂度：O(E+V)
     *
     * @param numCourses    需要修的课程数
     * @param prerequisites 所有待修习课程的依赖关系，int[]=<a,b>，表示想要修习课程 b，必须首先修习课程 a。
     * @return 是否可以完成所有课程的修习
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 以邻接表形式表示图
        List<List<Integer>> coursePredecessors = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            coursePredecessors.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            // 先修课程
            int predecessor = prerequisite[0];
            // 当前课程
            int current = prerequisite[1];
            // 根据先修课构建邻接表以及每门课程的入度
            coursePredecessors.get(current).add(predecessor);
            // 先修课程入度+1
            ++inDegree[predecessor];
        }

        Queue<Integer> queue = new ArrayDeque<>();
        // 将入度为 0(无选修课) 的结点入队，作为搜索起点。
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                queue.offer(course);
            }
        }

        // 开始排课
        int scheduledCourse = 0;
        while (!queue.isEmpty()) {
            // 要修习的课程
            Integer course = queue.poll();
            ++scheduledCourse;

            // 广度优先搜索：搜索 course 的所有前导课程
            for (int preCourse : coursePredecessors.get(course)) {
                // 减小先修课程入度
                --inDegree[preCourse];
                // 将入度为 0（没有先修课程/先修课程已修完） 的课程入队
                if (inDegree[preCourse] == 0) {
                    queue.offer(preCourse);
                }
            }
        }

        return scheduledCourse == numCourses;
    }

    /**
     * 广度优先搜索+拓扑排序
     * 时间复杂度：O(E+V)
     * 空间复杂度：O(E+V)
     *
     * @param numCourses    需要修的课程数
     * @param prerequisites 所有待修习课程的依赖关系，int[]=<a,b>，表示想要修习课程 b，必须首先修习课程 a。
     * @return 是否可以完成所有课程的修习
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        // 1. 初始化访问标志
        int[] courseScheduled = new int[numCourses];

        // 2. 初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        // 3. 构造邻接表
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[1];
            int predecessor = prerequisite[0];
            adjacency.get(course).add(predecessor);
        }

        for (int course = 0; course < numCourses; course++) {
            if (!dfs(adjacency, courseScheduled, course)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> coursePredecessors, int[] courseScheduled, int course) {
        if (courseScheduled[course] == 1) {
            // 已被当前结点启动的 DFS 访问过，说明有环。
            return false;
        }
        if (courseScheduled[course] == -1) {
            // 已被其他结点启动的 DFS 访问过，不必重复访问。
            return true;
        }

        // 本次访问标志
        courseScheduled[course] = 1;
        for (Integer predecessor : coursePredecessors.get(course)) {
            // 递归当前结点的邻接点，DFS 选择一个最近的，然后一直走到头。
            if (!dfs(coursePredecessors, courseScheduled, predecessor)) {
                return false;
            }
        }
        // 回溯标志
        courseScheduled[course] = -1;
        return true;
    }
}
