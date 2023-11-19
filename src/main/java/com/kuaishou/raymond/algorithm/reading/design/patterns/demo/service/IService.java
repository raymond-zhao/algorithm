package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 13:58
 * Description:
 */
public interface IService<P> {

    void createMaterial(P param);

//    IFactory<P, T> getFactory(Class<P> klass);

    void updateMaterial(P param);
}
