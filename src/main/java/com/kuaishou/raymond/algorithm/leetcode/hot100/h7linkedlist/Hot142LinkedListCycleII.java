package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import com.kuaishou.raymond.algorithm.leetcode.ListNode;

/**
 * 题目：<a href="https://leetcode.cn/problems/linked-list-cycle-ii/?envType=study-plan-v2&id=top-100-liked">142. 环形链表II</a>
 * 题目解析：<a href="https://leetcode.cn/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/">142. 环形链表II</a>
 * - Medium
 * - 链表
 * - 快慢指针
 * - 双指针
 */
public class Hot142LinkedListCycleII {

    /**
     * 快慢指针：fast 指针每次走两个单位，slow 指针每次走一个单位。
     * - 如果无环，fast 指针一定会走到 null，判定为无环。
     * - 如果有环，则 fast 与 slow 一定会在环中相遇，即 fast==slow，但不一定是在环的入口相遇。
     *  - 假设链表非环的结点为 a 个，环中的结点为 b 个，则链表的总结点为 a+b 个。
     *  - 假设 fast 走过的结点数为 f，slow 走过的结点数为 s。
     *  - 第一个关系，fast 的路程是 slow 的两倍，即 f = 2s。
     *  - 第二个关系，fast 比 slow 多走了 n 个环的周长，即 f = s + nb(n >= 1, 且 n \in N)
     *  - 关系1 - 关系2 可以得到：s=nb，f=2nb。
     * - 如果让指针从链表头部走到环的入口，并记走到环的入口的路程为 k，则 k=a+nb。
     * - 此时，slow 走过的结点个数为 nb，为了让 slow 走到环的入口，也就是要达到 k=a+nb，
     * - 显然，只需要让 slow 再走 a 个结点即可。
     * - 那么，如何让 slow 再走 a 个结点呢？
     * - 再回头开头，head 到环的入口的结点个数为 a 个，也就是说，可以考虑再用一个指针从 head 开始走起，这里可以复用 fast，
     * - 让 fast 重新从 head 开始，再走 a 个结点，即可与 slow 再次相遇。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        do {
            if (fast == null || fast.next == null) {
                // 如果可以走到链表尾部，说明无环。
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
