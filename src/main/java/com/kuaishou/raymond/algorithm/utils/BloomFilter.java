package com.kuaishou.raymond.algorithm.utils;

import java.util.BitSet;

/**
 * Author: raymond
 * CreateTime: 2023/5/18 13:28
 * 题目：布隆过滤器
 */
public class BloomFilter {

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(1000);

        // 添加元素到布隆过滤器
        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("orange");

        // 判断元素是否存在于布隆过滤器
        System.out.println(bloomFilter.contains("apple"));    // 输出: true
        System.out.println(bloomFilter.contains("pear"));     // 输出: false
        System.out.println(bloomFilter.contains("orange"));   // 输出: true
    }

    /**
     * 位图
     */
    private final BitSet bitSet;

    /**
     * 位图大小
     */
    private final int size;

    /**
     * 哈希种子
     */
    private final int[] seeds;

    public BloomFilter(int size) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.seeds = new int[]{2, 3, 5, 7, 11, 13, 17};
    }

    /**
     * 向 BloomFilter 添加元素
     */
    public void add(String value) {
        // 根据哈希种子哈希映射
        for (int seed : seeds) {
            // 每次根据种子计算出 1 个哈希值
            int hash = computeHash(value, seed);
            bitSet.set(hash, true);
        }
    }

    /**
     * 判断布隆过滤器中是否存在 value
     */
    public boolean contains(String value) {
        for (int seed : seeds) {
            int hash = computeHash(value, seed);
            // 如果位图中的某个位为 false，则 value 一定不存在。
            if (Boolean.FALSE.equals(bitSet.get(hash))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据种子与待添加的数据计算哈希值
     */
    private int computeHash(String value, int seed) {
        int hash = seed;

        // 对 value 中的每个字符进行哈希
        for (int i = 0; i < value.length(); i++) {
            // 一个简单的自定义哈希函数
            hash = (hash * seed + value.charAt(i)) % size;
        }

        return hash;
    }
}
