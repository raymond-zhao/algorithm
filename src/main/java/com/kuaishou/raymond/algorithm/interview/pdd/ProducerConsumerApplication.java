package com.kuaishou.raymond.algorithm.interview.pdd;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerApplication {

    private static final ExecutorService EXECUTORS = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        ProducerConsumerQueueUsingObject<Integer> queue = new ProducerConsumerQueueUsingObject<>();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        for (int i = 0; i < 2; i++) {
            // 2 个生产者线程
            EXECUTORS.submit(producer);
        }

        for (int i = 0; i < 2; i++) {
            // 2 个消费者线程
            EXECUTORS.submit(consumer);
        }

        EXECUTORS.shutdown();
    }

}

class Producer implements Runnable {

    private final ProducerConsumerQueueUsingObject<Integer> queue;

    public Producer(ProducerConsumerQueueUsingObject<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
           try {
               queue.offer(random.nextInt(10));
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
    }
}

class Consumer implements Runnable {

    private final ProducerConsumerQueueUsingObject<Integer> queue;

    public Consumer(ProducerConsumerQueueUsingObject<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}