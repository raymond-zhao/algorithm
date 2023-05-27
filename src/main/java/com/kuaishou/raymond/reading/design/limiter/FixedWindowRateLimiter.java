package com.kuaishou.raymond.reading.design.limiter;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-05-23 17:05
 */
public class FixedWindowRateLimiter {

    private static final int QPS = 2;

    private static final AtomicInteger REQ_COUNT = new AtomicInteger(0);

    private static final int TIMED_WINDOW = 1000; // ms

    private static long startTime = System.currentTimeMillis();

    public static synchronized boolean tryAcquire() {
        if ((System.currentTimeMillis() - startTime) > TIMED_WINDOW) {
            REQ_COUNT.set(0);
            startTime = System.currentTimeMillis();
        }
        return REQ_COUNT.incrementAndGet() <= QPS;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            LocalTime now = LocalTime.now();
            if (tryAcquire()) {
                System.out.println("已被限流，限流时间 = " + now);
            } else {
                System.out.println("未被限流，获取时间 = " + now);
            }
        }
    }
}
