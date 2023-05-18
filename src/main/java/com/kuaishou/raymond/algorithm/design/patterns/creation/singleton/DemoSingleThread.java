package com.kuaishou.raymond.algorithm.design.patterns.creation.singleton;

import java.util.Optional;

public class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused.");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);

        String accountId = "";
        Long accountIdLong = Optional.of(Long.valueOf(accountId)).orElse(0L);
        System.out.println("accountIdLong = " + accountIdLong);
    }
}