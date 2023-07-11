package com.kuaishou.raymond.demo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-07-10 13:22
 */
@Slf4j
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = calculateAsync(2, 0);

        completableFuture.thenAccept(number -> log.info("number = {}", number));

        completableFuture.exceptionally(throwable -> {
            log.error("Calculate error.", throwable);
            return 0;
        });

        completableFuture.handle((s, t) -> s != null ? s : 0);

        log.info("准备计算");
    }

    public static CompletableFuture<Object> calculateAsync(int a, int b) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return a / b;
        }, Executors.newFixedThreadPool(5));
    }
}
