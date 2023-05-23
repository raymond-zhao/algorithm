package com.kuaishou.raymond.interview.didi;

/**
 * Author: raymond
 * CreateTime: 2023/5/21 22:37
 * 题目：
 */
public class ZSet {

    private ZSkipListNode head;

    private ZSkipListNode tail;

    private long length;

    private int level;

    /**
     * 跳表结点
     */
    private static class ZSkipListNode {

        private String member;

        private double score;

        private ZSkipListNode backward;

        private ZSkipListLevel[] level;

    }

    /**
     * 跳表层级信息
     */
    private static class ZSkipListLevel {

        private ZSkipListNode forward;

        private long span;

    }
}
