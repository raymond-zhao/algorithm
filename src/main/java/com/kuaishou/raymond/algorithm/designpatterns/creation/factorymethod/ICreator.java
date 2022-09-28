package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod;


/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-26 21:16
 */
public interface ICreator {

    default void someOperation() {
        IProduct product = createProduct();
        product.doStuff();
    }

    IProduct createProduct();

}
