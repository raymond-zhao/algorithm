package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumerQueueUsingObject<E> {

    /**
     * 队列最大容量
     */
    private static final int MAX_SIZE = 4;

    /**
     * 存放实际元素的队列
     */
    private final Queue<E> queue;

    public ProducerConsumerQueueUsingObject() {
        queue = new ArrayDeque<>();
    }

    /**
     * 向队列中添加元素
     */
    public synchronized boolean offer(E e) throws InterruptedException {
        // 如果队列已满，需要阻塞生产者继续向其中添加元素。
        while (queue.size() == MAX_SIZE) {
            this.wait();
        }
        // 如果队列未满，生产者向其中添加元素。
        queue.offer(e);
        System.out.println(Thread.currentThread().getName() + " is producing element = [" + e + "], current size = " + queue.size());
        // 通知其他线程
        this.notifyAll();

        return true;
    }

    /**
     * 从队列中取元素
     */
    public synchronized E poll() throws InterruptedException {
        // 如果队列为空，需要阻塞消费者消费数据。
        while (queue.isEmpty()) {
            this.wait();
        }
        // 取数据
        E e = queue.poll();
        System.out.println(Thread.currentThread().getName() + " is consuming element [" + e + "]");
        // 通知其他线程
        this.notifyAll();

        return e;
    }
}
