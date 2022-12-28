package com.kuaishou.raymond.algorithm.design_patterns.creation.singleton;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 14:32
 */
public final class Singleton {

    private static Singleton instance;

    public String value;

    private Singleton(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }

}
