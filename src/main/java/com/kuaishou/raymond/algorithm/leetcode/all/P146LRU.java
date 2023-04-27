package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.HashMap;
import java.util.Map;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-02-22 14:07
 */
public class P146LRU<K, V> {

    /**
     * 存储实际数据的缓存
     * K: key
     * V: 双向链表
     */
    private final Map<K, DLinkedNode<K, V>> cache = new HashMap<>();

    /**
     * LRU 缓存中实际存储的元素数量
     */
    private int size;

    /**
     * LRU 缓存的最大存储容量，如果超过最大容量，则要淘汰链表尾部数据。
     */
    private final int capacity;

    private final DLinkedNode<K, V> head;

    private final DLinkedNode<K, V> tail;

    public P146LRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode<>();
        tail = new DLinkedNode<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 从 LRU 缓存中获取数据
     */
    public V get(K key) {
        DLinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            throw new NullPointerException();
        }
        // 把这个结点移动到链表头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 向 LRU 缓存中添加数据
     * 首先判断要插入的数据是否已经存在，
     * - 如果存在，则更新该 Node 的值，并将其移动到链表头部；
     * - 如果不存在，则将其直接插入到链表头部。
     */
    public void put(K key, V value) {
        if (capacity == 0) {
            throw new UnsupportedOperationException("This LRUCache has no capacity. Cannot put any value.");
        }
        DLinkedNode<K, V> node = cache.get(key);
        if (node == null) {
            DLinkedNode<K, V> newNode = new DLinkedNode<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 已达到最大容量，删除链表最后一个结点，同时清除缓存中的数据。
                DLinkedNode<K, V> last = removeLast();
                cache.remove(last.key);
                size--;
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
    private DLinkedNode<K, V> removeLast() {
        DLinkedNode<K, V> last = tail.prev;
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
    private void moveToHead(DLinkedNode<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 从链表中删除键结点
     * x <-> node <-> y
     * x <-> y
     */
    private void removeNode(DLinkedNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将结点添加到链表头部
     */
    private void addToHead(DLinkedNode<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private static class DLinkedNode<K, V> {

        private K key;

        private V value;

        private DLinkedNode<K, V> prev;

        private DLinkedNode<K, V> next;

        public DLinkedNode() {
            // 无参构造器
        }

        public DLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
