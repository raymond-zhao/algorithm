package com.kuaishou.raymond.algorithm.swordtooffer.day25_simulation_medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * ---
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 人脑的思维：栈的特性是后入先出，如果在出栈序列中先出了，那在入栈序列中一定时后入的。
 * - 查看出栈序列，查看第一个数字在入栈序列中的位置，然后查看出栈序列第二个元素是否在第一个元素的入栈序列的后边，
 * - 如果是，则表明到目前为止都是合法的出栈序列，继续执行此过程判断第三、第四个序列；
 * -
 */
public class P31 {

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        System.out.println("validateStackSequences(pushed, popped) = " + validateStackSequences(pushed, popped));
    }


    /**
     * 算法：模拟
     * 将 pushed 入栈序列依次压入辅助栈 stack，当入栈元素等于出栈元素时，将入栈元素出栈，并且移动出栈元素的指针。
     * 如果是合法序列，辅助栈最后的 size = 0。
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

}
