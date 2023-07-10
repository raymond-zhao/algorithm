package com.kuaishou.raymond.algorithm.interview.meituan;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-24 14:15
 */
public class MethodAreaOOM extends ClassLoader {

    public static void main(String[] args) {

        String demo = "Hello" + "JOINER" + "World";
        String[] joiners = demo.split("JOINER");
        for (String joiner : joiners) {
            System.out.println("joiner = " + joiner);
        }

        System.out.print("最大内存： ");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        System.out.print("可用内存： ");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + "MB");
        System.out.print("已使用内存： ");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB");

        int count = 0;
        try {
            for (int i = 0; i < 10000; i++) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(MethodAreaOOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
                //cglib 创建类文件
                enhancer.create();
                count = i;

            }
        } catch (Throwable e) {
            System.out.println("****count=" + count);
            e.printStackTrace();
        }
    }
}
