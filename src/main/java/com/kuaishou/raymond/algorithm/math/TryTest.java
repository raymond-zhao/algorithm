package com.kuaishou.raymond.algorithm.math;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-14 15:23
 */
@Slf4j
public class TryTest {

    public static void main(String[] args) {
        // log.info("cal1 = {}", cal(2, 0));
        cal3(2, 0);
        log.info("cal2 = {}", cal2(2, 0));
    }

    public static int cal(int a, int b) {
        return Try.of(() -> a / b).onFailure(throwable -> {
            log.error("0 不能做除数");
        }).get();
    }

    public static int cal2(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            log.error("0 不能做除数");
        }
        return 0;
    }

    public static void cal3(int a, int b) {
        Try.run(() -> log.info("res = {}", a / b)).onFailure(throwable -> {
            log.error("0 不能做除数");
        });
    }
}
