package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.kuaishou.raymond.algorithm.leetcode.Node;

/**
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/?envType=study-plan-v2&id=top-100-liked">138. 复制带随机指针的链表</a>
 * - 链表
 * - 哈希表
 */
public class Hot138CopyListWithRandomPointers {

    public Node copyRandomList(Node head) {
        // 存储当前链表中的所有结点
        Map<Node/*当前结点*/, Node/*具有当前结点的值，但不具备其 next 与 random 指针的结点。*/> map = new HashMap<>();
        Node node = head;

        // 第一次遍历，存储结点映射关系。
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        // 第二次遍历，连接结点。
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

}
