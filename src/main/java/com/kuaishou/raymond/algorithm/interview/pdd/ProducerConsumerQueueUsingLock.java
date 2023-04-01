package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueueUsingLock<E> {

    /**
     * 队列最大容量
     */
    private static final int MAX_SIZE = 4;

    /**
     * 存放实际元素的队列
     */
    private final Queue<E> queue;

    private final Lock lock = new ReentrantLock();

    private final Condition producerCondition = lock.newCondition();

    private final Condition consumerCondition = lock.newCondition();

    public ProducerConsumerQueueUsingLock() {
        queue = new ArrayDeque<>();
    }

    /**
     * 向队列中添加元素
     */
    public boolean offer(E e) throws InterruptedException {

        final Lock lock = this.lock;
        lock.lock();

        // 如果队列已满，需要阻塞生产者继续向其中添加元素。
        while (queue.size() == MAX_SIZE) {
            producerCondition.await();
        }
        // 如果队列未满，生产者向其中添加元素。
        queue.offer(e);
        System.out.println(Thread.currentThread().getName() + " is producing element = [" + e + "], current size = " + queue.size());
        // 通知其他线程
        consumerCondition.signalAll();

        lock.unlock();
        return true;
    }

    /**
     * 从队列中取元素
     */
    public E poll() throws InterruptedException {

        final Lock lock = this.lock;
        lock.lock();

        // 如果队列为空，需要阻塞消费者消费数据。
        while (queue.isEmpty()) {
            consumerCondition.await();
        }
        // 取数据
        E e = queue.poll();
        System.out.println(Thread.currentThread().getName() + " is consuming element [" + e + "]");
        // 通知其他线程
        producerCondition.signalAll();

        lock.unlock();
        return e;
    }

}
