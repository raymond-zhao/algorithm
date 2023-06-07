package com.kuaishou.raymond.algorithm.leetcode.hot100.h12stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author: raymond
 * CreateTime: 2023/5/2 16:34
 * 题目：<a href="https://leetcode.cn/problems/daily-temperatures/?envType=study-plan-v2&id=top-100-liked">739. 每日温度</a>
 * 考点：单调栈
 * 相似题目：
 * - <a href="https://leetcode.cn/problems/next-greater-element-i/">496. 下一个更大元素 I</a>
 * - <a href="https://leetcode.cn/problems/next-greater-element-ii/">503. 下一个更大元素 II</a>
 * - <a href="https://leetcode.cn/problems/next-greater-element-iii/">556. 下一个更大元素 III</a>
 * - <a href="https://leetcode.cn/problems/next-greater-element-iv/">2454. 下一个更大元素 IV</a>
 * - <a href="https://leetcode.cn/problems/online-stock-span/">901. 股票价格跨度</a>
 * - <a href="https://leetcode.cn/problems/trapping-rain-water/">42. 接雨水</a>
 * - <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">84. 柱状图中最大的矩形</a>
 */
public class Hot739DailyTemperatures {

    /**
     * 使用单调递减栈来保存递减的温度序列，当遍历到一个较高温度时，
     * 可以将栈中所有小于该温度的元素弹出，并记录它们的间隔天数为当前温度的下一个较高温度。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            int currentTemperature = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemperature) {
                // 如果栈非空，并且当前温度大于栈顶温度，则表示出现更高温度，更新栈中元素。
                Integer lowerTemperatureIdx = stack.pop();
                // 注意 answer 的下标刚出栈的栈顶元素，而不是 i。
                answer[lowerTemperatureIdx] = i - lowerTemperatureIdx;
            }
            stack.push(i);
        }
        return answer;
    }

    /**
     * 暴力解 O(n^2)
     * 超时，不能 AC。
     */
    public int[] dailyTemperaturesBF(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }

}
