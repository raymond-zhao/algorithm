package com.kuaishou.raymond.algorithm.leetcode.hot100.h7linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: raymond
 * CreateTime: 2023/4/30 15:55
 * 题目链接：<a href="https://leetcode.cn/problems/lru-cache/?envType=study-plan-v2&id=top-100-liked">146. LRU 缓存</a>
 * - 链表
 * - 哈希表
 * - LRU
 */
public class Hot146LRUCache {

    private final Map<Integer, Node> cache;

    private final int capacity;

    private int size;

    private final Node head;

    private final Node tail;

    public Hot146LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>();

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 从 LRU 缓存中读取数据
     * - 如果缓存中不包含该结点，则直接返回 null。
     * - 如果缓存中包含该 key，则
     *  - 先将该结点移动到链表头部
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 向 LRU 缓存中添加数据
     * - 如果之前存在该key，则更新该 key 对应的 value，并将对应的结点移动到链表头部，size 不做改变。
     * - 如果之前不存在该key
     *  - 判断该 size 是否达到 capacity，如果达到则拒绝插入，抛出异常。
     *  - 如果未达到 capacity
     *   - 生成结点，并将结点添加到缓存中，结点插入到链表头部。
     *   - ++size，如果 size > capacity，则移除链表最后一个节点，移除缓存中的数据,之后再 --size。
     */
    public void put(int key, int value) {
        if (capacity <= 0) {
            throw new UnsupportedOperationException("This LRUCache has no capacity. Cannot put any value.");
        }
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 删除链表尾部数据结点
     * y <-> x <-> tail
     * y <-> tail
     */
    private Node removeTail() {
        Node last = tail.prev;
        removeNode(last);
        return last;
    }

    /**
     * 将 node 移动到链表头部
     * 1. 先在链表中删除当前结点；
     * 2. 再把当前结点添加到链表头部。
     * head <-> tail
     * head <-> node <->tail
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 从链表中删除键结点
     * x <-> node <-> y
     * x <-> y
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将结点添加到链表头部
     */
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public static class Node {

        private int key;

        private int value;

        private Node prev;

        private Node next;

        public Node() {

        }

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}
