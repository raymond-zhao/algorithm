package com.kuaishou.raymond.reading.design.patterns.creation.singleton;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-10-18 10:35
 */
public class DemoMultiThreadSingleton {

    public static void main(String[] args) {
//        Thread foo = new Thread(new ThreadFoo());
//        Thread bar = new Thread(new ThreadBar());
//        foo.start();
//        bar.start();
//

//        new Thread(() -> {
//            Singleton singleton = Singleton.getInstance("FOO");
//            System.out.println(singleton.value);
//        }).start();
//
//        new Thread(() -> {
//            Singleton singleton = Singleton.getInstance("BAR");
//            System.out.println(singleton.value);
//        }).start();

        new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("FOO");
            System.out.println(singleton.value);
        }).start();

        new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("BAR");
            System.out.println(singleton.value);
        }).start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }
}
