package com.kuaishou.raymond.algorithm.interview.bytedance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: raymond
 * CreateTime: 2023/6/18 22:35
 * 题目：多线程交替打印
 */
public class PrintNumbers {

    public static void main(String[] args) {
        printNumbers(10, 20);
    }


    public static void printNumbers(int nThreads, int num) {
        AtomicInteger counter = new AtomicInteger(1);
        Executor executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < num; i++) {
            executor.execute(() -> {
                System.out.println(counter.getAndIncrement());
            });
        }
    }
}
