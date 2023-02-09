package com.kuaishou.raymond.algorithm.swordtooffer.day2_node_easy;

import com.kuaishou.raymond.algorithm.swordtooffer.RandomNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 复杂链表的复制
 */
public class P35CopyRandomList {

    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }
        RandomNode cur = head;
        Map<RandomNode, RandomNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new RandomNode(cur.val));
            cur = cur.next;
        }
        cur = head;

        while (cur != null) {
            // 当前结点对应的新结点的下一个结点 = 当前节点下一个结点对应的新节点
            map.get(cur).next = map.get(cur.next);
            // 当前结点对应的新结点的 Random 结点 = 当前节点 Random 结点对应的新节点
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 返回链表头
        return map.get(head);
    }

}
