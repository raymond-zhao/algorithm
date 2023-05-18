package com.kuaishou.raymond.algorithm.design.patterns.creation.singleton;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-10-18 10:59
 */
public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;

    public String value;

    private ThreadSafeSingleton(String value) {
        this.value = value;
    }

    public static ThreadSafeSingleton getInstance(String value) {
        if (instance != null) {
            return instance;
        }

        synchronized (ThreadSafeSingleton.class) {
            if (instance == null) {
                instance = new ThreadSafeSingleton(value);
            }
            return instance;
        }
    }

}
